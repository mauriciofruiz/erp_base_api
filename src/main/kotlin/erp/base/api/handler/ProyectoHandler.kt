package erp.base.api.handler

import erp.base.api.models.Proyecto
import erp.base.api.service.ProyectoService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ProyectoHandler(
    private val tbaProyectoService: ProyectoService,
) {

    fun getAllProjectsActive(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaProyectoService.findAllProjectsActive(), Proyecto::class.java)
}
