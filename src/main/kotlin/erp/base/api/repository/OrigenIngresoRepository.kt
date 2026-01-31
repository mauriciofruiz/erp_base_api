package erp.base.api.repository

import erp.base.api.models.OrigenIngreso
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrigenIngresoRepository : ReactiveCrudRepository<OrigenIngreso, Int>
