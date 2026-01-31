package erp.base.api.handler

import erp.base.api.models.TipoVehiculo
import erp.base.api.service.TipoVehiculoService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class TipoVehiculoHandler(
    private val tbaTipoVehiculoService: TipoVehiculoService,
) {

    fun create(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(TipoVehiculo::class.java)
            .flatMap(tbaTipoVehiculoService::createVehicleType)
            .flatMap { tipoVehiculo -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(tipoVehiculo) }

    fun findAll(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaTipoVehiculoService.findAll(), TipoVehiculo::class.java)

    fun findById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return tbaTipoVehiculoService.findById(id)
            .flatMap { tipoVehiculo -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(tipoVehiculo) }
    }

    fun delete(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return tbaTipoVehiculoService.delete(id)
            .then(ServerResponse.noContent().build())
    }
}
