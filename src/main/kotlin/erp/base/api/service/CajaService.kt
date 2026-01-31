package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.exception.CustomException
import erp.base.api.models.Caja
import erp.base.api.repository.CajaRepository
import erp.base.api.utils.Paginacion
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CajaService(private val tbaCajaRepository: CajaRepository) {

    fun findCajaByIdSucusal(id: Int): Flux<Caja> =
        tbaCajaRepository.findByIdSucursal(id).switchIfEmpty(Flux.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BOX_NOT_FOUND + id)))

    fun findById(id: Int): Mono<Caja> =
        tbaCajaRepository.findById(id).switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BOX_NOT_FOUND + id)))

    fun getRequestBySearchParams(
        branchId: Int?,
        name: String?,
        page: Int,
        limit: Int,
        sortDirection: String?,
        sortfield: String?
    ): Mono<Paginacion<Caja>> {
        val cajaFlux = tbaCajaRepository.getBySearchParams(branchId, name, sortfield, sortDirection, limit, (page - 1) * limit)
        val total = tbaCajaRepository.getCountBySearchParams(branchId, name)
        return total.flatMap { totalCount -> cajaFlux.collectList().map { cajaList -> Paginacion(cajaList, page, limit, totalCount.toInt()) } }
    }

    fun createCaja(caja: Caja): Mono<Caja> =
        validateAndSaveCaja(null, caja)

    fun updateCaja(id: Int, caja: Caja): Mono<Caja> =
        validateAndSaveCaja(id, caja)

    fun validateAndSaveCaja(id: Int?, caja: Caja): Mono<Caja> {
        val cajaMono = if (id != null) tbaCajaRepository.findById(id) else Mono.just(Caja())
        return cajaMono.flatMap {
            val newCaja = Caja(
                idCaja = id,
                idCuenta = caja.idCuenta,
                idSucursal = caja.idSucursal,
                nombreCaja = caja.nombreCaja,
                numeroCaja = caja.numeroCaja,
                secuenciaFactura = caja.secuenciaFactura,
                secuenciaGuia = caja.secuenciaGuia,
                secuenciaNc = caja.secuenciaNc,
                secuenciaNd = caja.secuenciaNd,
                secuenciaRetencion = caja.secuenciaRetencion,
                estadoCaja = caja.estadoCaja,
                secuenciaLiquidacion = caja.secuenciaLiquidacion
            )
            tbaCajaRepository.save(newCaja)
        }
    }
}
