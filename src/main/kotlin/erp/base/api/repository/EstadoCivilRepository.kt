package erp.base.api.repository

import erp.base.api.models.EstadoCivil
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EstadoCivilRepository : ReactiveCrudRepository<EstadoCivil, Int>
