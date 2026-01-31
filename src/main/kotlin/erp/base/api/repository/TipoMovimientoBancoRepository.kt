package erp.base.api.repository

import erp.base.api.models.TipoMovimientoBanco
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TipoMovimientoBancoRepository : ReactiveCrudRepository<TipoMovimientoBanco, Int>
