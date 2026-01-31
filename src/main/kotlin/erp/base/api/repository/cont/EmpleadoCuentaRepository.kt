package erp.base.api.repository.cont

import erp.base.api.models.EmpleadoCuenta
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface EmpleadoCuentaRepository : ReactiveCrudRepository<EmpleadoCuenta, Int> {

    fun findByIdEmpleado(idEmpleado: Int): Flux<EmpleadoCuenta>
}
