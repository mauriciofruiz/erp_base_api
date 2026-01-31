package erp.base.api.repository

import erp.base.api.models.Sorteo
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SorteoRepository : ReactiveCrudRepository<Sorteo, Int>
