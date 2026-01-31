package erp.base.api.repository

import erp.base.api.models.Cargo
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CargoRepository : ReactiveCrudRepository<Cargo, Int>
