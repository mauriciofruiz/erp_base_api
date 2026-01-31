package erp.base.api.repository

import erp.base.api.models.EstadoDeposito
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EstadoDepositoRepository : ReactiveCrudRepository<EstadoDeposito, Int>
