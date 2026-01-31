package erp.base.api.controller

import erp.base.api.Preference
import erp.base.api.dto.BodegaDto
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.function.Consumer

@Service
class InventarioController(
    preference: Preference
) {
    private val webClient: WebClient = WebClient.builder()
        .baseUrl(preference.getInventarioService())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()

    fun createWareHouse(bodegaDtoList: List<BodegaDto>, headers: Consumer<HttpHeaders>): Mono<Void> =
        webClient
            .post()
            .uri("/bodega")
            .headers(headers)
            .bodyValue(bodegaDtoList)
            .retrieve()
            .bodyToMono(Void::class.java)

    fun updateWareHouse(bodegaDtoList: List<BodegaDto>, headers: Consumer<HttpHeaders>): Mono<Void> =
        webClient
            .put()
            .uri("/bodega")
            .headers(headers)
            .bodyValue(bodegaDtoList)
            .retrieve()
            .bodyToMono(Void::class.java)
}
