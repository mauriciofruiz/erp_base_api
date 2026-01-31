package erp.base.api.repository

import erp.base.api.models.Factura
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FacturaRepository : ReactiveCrudRepository<Factura, Int>
