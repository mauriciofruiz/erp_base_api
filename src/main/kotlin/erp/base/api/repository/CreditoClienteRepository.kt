package erp.base.api.repository

import erp.base.api.models.CreditoCliente
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface CreditoClienteRepository : ReactiveCrudRepository<CreditoCliente, Int> {

    fun findByIdCliente(clientId: Int): Mono<CreditoCliente>
}
