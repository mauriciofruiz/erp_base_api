package erp.base.api.repository

import erp.base.api.models.EstadoSRI
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EstadoSRIRepository : ReactiveCrudRepository<EstadoSRI, Int>
