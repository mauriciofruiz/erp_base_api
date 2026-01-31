package erp.base.api.repository

import erp.base.api.models.TipoPago
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TipoPagoRepository : ReactiveCrudRepository<TipoPago, Int>
