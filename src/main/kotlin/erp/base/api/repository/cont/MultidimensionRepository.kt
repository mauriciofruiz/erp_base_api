package erp.base.api.repository.cont

import erp.base.api.models.Multidimension
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MultidimensionRepository : ReactiveCrudRepository<Multidimension, Int>
