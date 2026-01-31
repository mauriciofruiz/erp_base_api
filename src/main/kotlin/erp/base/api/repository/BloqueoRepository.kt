package erp.base.api.repository

import erp.base.api.models.Bloqueo
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BloqueoRepository : ReactiveCrudRepository<Bloqueo, Int>
