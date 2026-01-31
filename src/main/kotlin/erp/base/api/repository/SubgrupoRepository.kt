package erp.base.api.repository

import erp.base.api.models.SubGrupo
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SubgrupoRepository : ReactiveCrudRepository<SubGrupo, Int>
