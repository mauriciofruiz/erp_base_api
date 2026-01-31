package erp.base.api.repository

import erp.base.api.models.Cupon
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CuponRepository : ReactiveCrudRepository<Cupon, Int>
