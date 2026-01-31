package erp.base.api.repository

import erp.base.api.models.FacturaProveedor
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface FacturaProveedorRepository : ReactiveCrudRepository<FacturaProveedor, Int> {

    fun findByIdProveedorAndIdEstadoFacturaProveedorNotIn(
        idProveedor: Int,
        idEstadoFacturaProveedorList: List<Int>
    ): Flux<FacturaProveedor>
}
