package erp.base.api.repository

import erp.base.api.models.ImpuestoDocumento
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface ImpuestoDocumentoRepository : ReactiveCrudRepository<ImpuestoDocumento, Int> {

    fun findAllByIdDocumentoAndEstadoImpuesto(idDocumento: Long, estadoImpuesto: Boolean): Flux<ImpuestoDocumento>

    fun findAllByIdTipoDocumentoAndIdVoucherAndEstadoImpuesto(
        idTipoDocumento: Int,
        idVoucher: Int,
        estadoImpuesto: Boolean
    ): Flux<ImpuestoDocumento>
}
