package erp.base.api.repository

import erp.base.api.models.TipoComprobante
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TipoComprobanteRepository : ReactiveCrudRepository<TipoComprobante, Int>
