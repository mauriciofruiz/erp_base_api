package erp.base.api.repository

import erp.base.api.models.Retencion
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface RetencionRepository : ReactiveCrudRepository<Retencion, Int> {

    fun findByEstadoRetencionAndEsProveedorAndIdTipoRetencion(
        estadoRetencion: Boolean,
        esProveedor: Boolean,
        idTipoRetencion: Int
    ): Flux<Retencion>

    fun findByEstadoRetencionAndEsClienteAndIdTipoRetencion(
        estadoRetencion: Boolean,
        esCliente: Boolean,
        idTipoRetencion: Int
    ): Flux<Retencion>
}
