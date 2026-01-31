package erp.base.api.repository.rrh

import erp.base.api.models.rrh.ContactoEmergencia
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface ContactoEmergenciaRepository : ReactiveCrudRepository<ContactoEmergencia, Int> {

    fun findByIdEmpleado(idEmpleado: Int): Flux<ContactoEmergencia>
}
