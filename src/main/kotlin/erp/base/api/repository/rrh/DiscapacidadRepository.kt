package erp.base.api.repository.rrh

import erp.base.api.models.rrh.Discapacidad
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DiscapacidadRepository : ReactiveCrudRepository<Discapacidad, Int>
