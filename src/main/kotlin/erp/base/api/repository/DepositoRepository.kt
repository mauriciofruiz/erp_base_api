package erp.base.api.repository

import erp.base.api.models.Deposito
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DepositoRepository : ReactiveCrudRepository<Deposito, Int>
