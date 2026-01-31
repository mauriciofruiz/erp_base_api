package erp.base.api.repository.rrh

import erp.base.api.models.rrh.CuentaBancaria
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface CuentaBancariaRepository : ReactiveCrudRepository<CuentaBancaria, Int> {

    fun existsByNumeroCuentaBancaria(numeroCuenta: String): Mono<Boolean>
    fun findByIdEmpleado(idEmpleado: Int): Flux<CuentaBancaria>
}
