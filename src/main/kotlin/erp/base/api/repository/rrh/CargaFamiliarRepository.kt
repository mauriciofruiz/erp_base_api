package erp.base.api.repository.rrh

import erp.base.api.models.rrh.CargaFamiliar
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface CargaFamiliarRepository : ReactiveCrudRepository<CargaFamiliar, Int> {

    fun existsByCedulaCargaFml(cedula: String): Mono<Boolean>
    fun findByIdEmpleado(idEmpleado: Int): Flux<CargaFamiliar>
}
