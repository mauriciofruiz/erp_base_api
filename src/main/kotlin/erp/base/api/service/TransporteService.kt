package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.response.TransporteResponse
import erp.base.api.exception.CustomException
import erp.base.api.models.Transporte
import erp.base.api.repository.TransporteRepository
import erp.base.api.repository.custom.TransporteCustom
import erp.base.api.utils.Paginacion
import erp.base.api.utils.Utils
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.ZonedDateTime

@Service
class TransporteService(
    private val tbaTransporteRepository: TransporteRepository,
    private val tbaTipoVehiculoService: TipoVehiculoService,
    private val tbaTransporteCustom: TransporteCustom
) {

    fun createTransportation(tbaTransporte: Transporte): Mono<Void> {
        tbaTransporte.idTransporte = null
        return tbaTransporteRepository.save(tbaTransporte).then()
    }

    fun updateTransportation(id: Int, tbaTransporte: Transporte): Mono<Void> =
        tbaTransporteRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.TRANSPORTATION_NOT_FOUND + id)))
            .flatMap {
                tbaTransporte.idTransporte = id
                tbaTransporteRepository.save(tbaTransporte)
            }.then()

    fun patchTransportation(id: Int, tbaTransporte: Transporte): Mono<Transporte> =
        tbaTransporteRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.TRANSPORTATION_NOT_FOUND + id)))
            .flatMap { tbaTransporte1 ->
                tbaTransporte.idTipoVehiculo?.let { tbaTransporte1.idTipoVehiculo = it }
                tbaTransporte.nombreContacto?.let { tbaTransporte1.nombreContacto = it }
                tbaTransporte.identificacionContacto?.let { tbaTransporte1.identificacionContacto = it }
                tbaTransporte.numeroContacto?.let { tbaTransporte1.numeroContacto = it }
                tbaTransporte.placaTransporte?.let { tbaTransporte1.placaTransporte = it }
                tbaTransporte.sectorTransporte?.let { tbaTransporte1.sectorTransporte = it }
                tbaTransporte.provinciaTransporte?.let { tbaTransporte1.provinciaTransporte = it }
                tbaTransporte.detalleTransporte?.let { tbaTransporte1.detalleTransporte = it }
                tbaTransporte.pesoMinimoTransporte?.let { tbaTransporte1.pesoMinimoTransporte = it }
                tbaTransporte.pesoMaximoTransporte?.let { tbaTransporte1.pesoMaximoTransporte = it }
                tbaTransporte.matriculaTransporte?.let { tbaTransporte1.matriculaTransporte = it }
                tbaTransporte.internoTransporte?.let { tbaTransporte1.internoTransporte = it }
                tbaTransporte.inactivoTransporte?.let { tbaTransporte1.inactivoTransporte = it }
                tbaTransporteRepository.save(tbaTransporte1)
            }

    fun getTransportationById(id: Int): Mono<TransporteResponse> =
        tbaTransporteRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.TRANSPORTATION_NOT_FOUND + id)))
            .flatMap { tbaTransporte ->
                tbaTipoVehiculoService.findById(tbaTransporte.idTipoVehiculo!!)
                    .map { tbaTipoVehiculo ->
                        TransporteResponse(
                            idUsuarioCreacion = tbaTransporte.idUsuarioCreacion,
                            idUsuarioModificacion = tbaTransporte.idUsuarioModificacion,
                            fechaCreacion = tbaTransporte.fechaCreacion,
                            fechaModificacion = tbaTransporte.fechaModificacion,
                            idTransporte = tbaTransporte.idTransporte,
                            idTipoVehiculo = tbaTipoVehiculo.idTipoVehiculo,
                            nombreTipoVehiculo = tbaTipoVehiculo.nombreTipoVehiculo,
                            nombreContacto = tbaTransporte.nombreContacto,
                            identificacionContacto = tbaTransporte.identificacionContacto,
                            numeroContacto = tbaTransporte.numeroContacto,
                            placaTransporte = tbaTransporte.placaTransporte,
                            sectorTransporte = tbaTransporte.sectorTransporte,
                            provinciaTransporte = tbaTransporte.provinciaTransporte,
                            detalleTransporte = tbaTransporte.detalleTransporte,
                            pesoMinimoTransporte = tbaTransporte.pesoMinimoTransporte,
                            pesoMaximoTransporte = tbaTransporte.pesoMaximoTransporte,
                            matriculaTransporte = tbaTransporte.matriculaTransporte,
                            internoTransporte = tbaTransporte.internoTransporte,
                            inactivoTransporte = tbaTransporte.inactivoTransporte
                        )
                    }
            }

    fun getTransportationsBySearchParams(
        vehicleTypeName: String?,
        contactName: String?,
        sector: String?,
        province: String?,
        internal: Boolean?,
        inactive: Boolean?,
        startDateParam: ZonedDateTime?,
        endDateParam: ZonedDateTime?,
        byCurrentMonth: Boolean?,
        page: Int,
        limit: Int,
        sortDirection: String?,
        sortField: String?
    ): Mono<Paginacion<TransporteResponse>> {
        val startDate = Utils.getStartDateForPagination(byCurrentMonth ?: false, startDateParam)
        val endDate = Utils.getEndDateForPagination(byCurrentMonth ?: false, endDateParam)
        val tbaTransporteResponseFlux = tbaTransporteCustom.getBySearchParams(vehicleTypeName, contactName, sector, province, internal, inactive, startDate, endDate, sortField, sortDirection, limit, (page - 1) * limit)
        val total = tbaTransporteCustom.getCountBySearchParams(vehicleTypeName, contactName, sector, province, internal, inactive, startDate, endDate)
        return total.flatMap { totalCount ->
            tbaTransporteResponseFlux.collectList().map { list -> Paginacion(list, page, limit, totalCount.toInt()) }
        }.onErrorMap { e -> CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "") }
    }

    fun getAllActiveTransportistas(paramSearch: String?): Flux<Transporte> {
        val search = paramSearch ?: ""
        val identification = if (Utils.validateNumbers(search)) paramSearch else null
        val completeName = if (!Utils.validateNumbers(search)) paramSearch else null
        return tbaTransporteRepository.getAllActiveTransportistas(completeName, identification)
    }

    fun getTransportationByCiAndPlaca(ci: String, placa: String): Mono<Transporte> =
        tbaTransporteRepository.findByIdentificacionContactoAndPlacaTransporte(ci, placa)
}
