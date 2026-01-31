package erp.base.api.handler

import erp.base.api.dto.response.TransporteResponse
import erp.base.api.exception.CustomException
import erp.base.api.models.Transporte
import erp.base.api.service.TransporteService
import erp.base.api.utils.Utils
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.time.ZonedDateTime

@Component
class TransporteHandler(
    private val transporteService: TransporteService,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun create(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(Transporte::class.java)
            .flatMap { transporteService.createTransportation(it) }
            .then(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue("Transacción creada correctamente"))
            .onErrorResume { ServerResponse.badRequest().bodyValue("Error al crear la transacción") }

    fun update(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return request.bodyToMono(Transporte::class.java)
            .flatMap { transporte -> transporteService.updateTransportation(id, transporte) }
            .then(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue("Transacción actualizada correctamente"))
            .onErrorResume { ServerResponse.badRequest().bodyValue("Error al actualizar la transacción") }
    }

    fun patch(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return request.bodyToMono(Transporte::class.java)
            .flatMap { transporte -> transporteService.patchTransportation(id, transporte) }
            .flatMap { transporte -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(transporte) }
    }

    fun getById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(transporteService.getTransportationById(id), TransporteResponse::class.java)
    }

    fun getBySearchParams(request: ServerRequest): Mono<ServerResponse> {
        val vehicleTypeName = request.queryParam("vehicleTypeName").orElse(null)
        val contactName = request.queryParam("contactName").orElse(null)
        val sector = request.queryParam("sector").orElse(null)
        val province = request.queryParam("province").orElse(null)
        val internal = request.queryParam("internal").orElse("false").toBoolean()
        val inactive = request.queryParam("inactive").orElse("false").toBoolean()
        val startDate = request.queryParam("startDate").orElse(null)
        val startDateParam: ZonedDateTime? = startDate?.let { Utils.parseStringToZonedDateTime(it) }
        val endDate = request.queryParam("endDate").orElse(null)
        val endDateParam: ZonedDateTime? = endDate?.let { Utils.parseStringToZonedDateTime(it) }
        val page = request.queryParam("page").orElse("1").toInt()
        val limit = request.queryParam("limit").orElse("10").toInt()
        val sortDirection = request.queryParam("sortDirection").orElse(null)
        val sortField = request.queryParam("sortField").orElse(null)
        val byCurrentMonth = request.queryParam("byCurrentMonth").orElse("false").toBoolean()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(transporteService.getTransportationsBySearchParams(vehicleTypeName, contactName, sector, province, internal, inactive, startDateParam, endDateParam, byCurrentMonth, page, limit, sortDirection, sortField), TransporteResponse::class.java)
            .onErrorMap { e -> CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "") }
    }

    fun getAllActiveTransportistas(request: ServerRequest): Mono<ServerResponse> {
        val paramSearch = request.queryParam("param").orElse(null)
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(transporteService.getAllActiveTransportistas(paramSearch), Transporte::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getTransportationByParams(request: ServerRequest): Mono<ServerResponse> {
        val ci = request.queryParam("ci").orElse(null)
        val placa = request.queryParam("placa").orElse(null)
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(transporteService.getTransportationByCiAndPlaca(ci, placa), Transporte::class.java)
    }
}
