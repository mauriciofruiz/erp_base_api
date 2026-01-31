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
class VentasController(
    preference: Preference
) {
    private val webClient: WebClient = WebClient.builder()
        .baseUrl(preference.getVentasService())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()

    fun getEstadoCuentaCobrar(clientId: Int, headers: HttpHeaders): Mono<EstadoCuentoPagarDto> =
        webClient
            .get()
            .uri("/factura-cuota/cliente/$clientId")
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
                        "${ErrorMessage.ERROR_GETTING_DATA_CONTABILIDAD} - ${ErrorMessage.PROVIDER_NOT_FOUND}$clientId"
                    )
                )
            }
}
