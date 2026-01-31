package erp.base.api.repository

import erp.base.api.models.PaginaRol
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PaginaRolRepository : ReactiveCrudRepository<PaginaRol, Int>
