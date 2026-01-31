package erp.base.api.repository

import erp.base.api.models.Desbloqueo
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DesbloqueoRepository : ReactiveCrudRepository<Desbloqueo, Int>
