package erp.base.api.repository

import erp.base.api.models.Grupo
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GrupoRepository : ReactiveCrudRepository<Grupo, Int>
