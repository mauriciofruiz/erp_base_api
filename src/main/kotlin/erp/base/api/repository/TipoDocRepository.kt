package erp.base.api.repository

import erp.base.api.models.TipoDoc
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TipoDocRepository : ReactiveCrudRepository<TipoDoc, Int>
