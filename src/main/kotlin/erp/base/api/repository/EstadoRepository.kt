package erp.base.api.repository

import erp.base.api.models.Estado
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EstadoRepository : ReactiveCrudRepository<Estado, Int>
