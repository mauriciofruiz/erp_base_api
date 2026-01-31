package erp.base.api.service

import erp.base.api.models.TipoComprobante
import erp.base.api.repository.TipoComprobanteRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class TipoComprobanteService(private val tipoComprobanteRepository: TipoComprobanteRepository) {

    fun getAllTipoComprobante(): Flux<TipoComprobante> =
        tipoComprobanteRepository.findAll()
}
