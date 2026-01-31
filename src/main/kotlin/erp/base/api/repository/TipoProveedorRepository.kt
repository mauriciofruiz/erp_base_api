package erp.base.api.repository

import erp.base.api.models.TipoProveedor
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TipoProveedorRepository : ReactiveCrudRepository<TipoProveedor, Int>
