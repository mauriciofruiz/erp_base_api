package erp.base.api.repository

import erp.base.api.models.Genero
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GeneroRepository : ReactiveCrudRepository<Genero, Int>
