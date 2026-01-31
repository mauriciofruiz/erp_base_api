package erp.base.api.repository.rrh

import erp.base.api.models.rrh.TipoContrato
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TipoContratoRepository : ReactiveCrudRepository<TipoContrato, Int>
