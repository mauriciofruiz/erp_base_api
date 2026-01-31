package erp.base.api.service

import erp.base.api.models.CreditoCliente
import erp.base.api.repository.CreditoClienteRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CreditoClienteService(private val tbaCreditoClienteRepository: CreditoClienteRepository) {

    fun saveCredit(tbaCreditoCliente: CreditoCliente): Mono<CreditoCliente> =
        tbaCreditoClienteRepository.save(tbaCreditoCliente)

    fun getCreditByClientId(clientId: Int): Mono<CreditoCliente> =
        tbaCreditoClienteRepository.findByIdCliente(clientId)
}
