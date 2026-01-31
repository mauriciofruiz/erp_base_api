package erp.base.api.repository.rrh

import erp.base.api.models.rrh.ContratoLaboral
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface ContratoLaboralRepository : ReactiveCrudRepository<ContratoLaboral, Int> {

    fun findByIdEmpleado(idEmpleado: Int): Flux<ContratoLaboral>
}
