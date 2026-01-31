package erp.base.api.service

import erp.base.api.models.ImpuestoDocumento
import erp.base.api.repository.ImpuestoDocumentoRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ImpuestoDocumentoService(private val impuestoDocumentoRepository: ImpuestoDocumentoRepository) {

    fun impuestoDocumentoByIdDocumento(idDocumento: Long): Flux<ImpuestoDocumento> =
        impuestoDocumentoRepository.findAllByIdDocumentoAndEstadoImpuesto(idDocumento, true)

    fun impuestoDocumentoByIdTipoDocumentoAndIdVoucher(idTipoDocumento: Int, idVoucher: Int): Flux<ImpuestoDocumento> =
        impuestoDocumentoRepository.findAllByIdTipoDocumentoAndIdVoucherAndEstadoImpuesto(idTipoDocumento, idVoucher, true)
}
