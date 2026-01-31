package erp.base.api.repository

import erp.base.api.models.Pais
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PaisRepository : ReactiveCrudRepository<Pais, Int>
