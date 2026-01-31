package erp.base.api.repository

import erp.base.api.models.Adquiriente
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface AdquirienteRepository : ReactiveCrudRepository<Adquiriente, Int> {

    fun findAllByEstadoAdquiriente(estadoAdquiriente: Boolean): Flux<Adquiriente>
}

