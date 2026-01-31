package erp.base.api.controller

import erp.base.api.Preference
import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.response.EstadoCuentoPagarDto
import erp.base.api.exception.CustomException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class ContabilidadController(
    preference: Preference
) {
    private val webClient: WebClient = WebClient.builder()
        .baseUrl(preference.getContabilidadService())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()

    fun getEstadoCuentaPagar(idProveedor: Int, headers: HttpHeaders): Mono<EstadoCuentoPagarDto> =
        webClient
            .get()
            .uri("/factura-cuota/proveedor/$idProveedor")
            .headers { httpHeaders -> httpHeaders.addAll(headers) }
            .retrieve()
            .onStatus(
                { status -> status.is4xxClientError || status.is5xxServerError },
                { clientResponse ->
                    clientResponse.bodyToMono(String::class.java)
                        .flatMap { errorMessage ->
                            Mono.error(
                                CustomException(clientResponse.statusCode() as HttpStatus, errorMessage)
                            )
                        }
                }
            )
            .bodyToMono(EstadoCuentoPagarDto::class.java)
            .onErrorResume {
                Mono.error(
                    CustomException(
                        HttpStatus.NOT_FOUND,
                        "${ErrorMessage.ERROR_GETTING_DATA_CONTABILIDAD} - ${ErrorMessage.PROVIDER_NOT_FOUND}$idProveedor"
                    )
                )
            }
}
