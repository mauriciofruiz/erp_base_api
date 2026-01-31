package erp.base.api.repository

import erp.base.api.models.TbaRol
import erp.base.api.repository.custom.TbaRolRepositoryCustom
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TbaRolRepository : ReactiveCrudRepository<TbaRol, Int>, TbaRolRepositoryCustom
