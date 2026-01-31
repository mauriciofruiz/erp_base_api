package erp.base.api.repository

import erp.base.api.models.DetDeposito
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DetDepositoRepository : ReactiveCrudRepository<DetDeposito, Int>
