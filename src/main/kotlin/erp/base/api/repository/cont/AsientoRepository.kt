package erp.base.api.repository.cont

import erp.base.api.models.Asiento
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AsientoRepository : ReactiveCrudRepository<Asiento, Int>
