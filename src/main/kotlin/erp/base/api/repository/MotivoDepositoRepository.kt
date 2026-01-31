package erp.base.api.repository

import erp.base.api.models.MotivoDeposito
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MotivoDepositoRepository : ReactiveCrudRepository<MotivoDeposito, Int>
