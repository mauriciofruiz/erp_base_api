package erp.base.api.repository

import erp.base.api.models.Pagina
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PaginaRepository : ReactiveCrudRepository<Pagina, Int>
