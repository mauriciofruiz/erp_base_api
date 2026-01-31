package erp.base.api.repository

import erp.base.api.models.PagoFacturaProveedor
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PagoFacturaProveedorRepository : ReactiveCrudRepository<PagoFacturaProveedor, Int>
