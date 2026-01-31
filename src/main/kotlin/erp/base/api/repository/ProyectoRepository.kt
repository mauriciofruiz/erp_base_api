package erp.base.api.repository

import erp.base.api.models.Proyecto
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface ProyectoRepository : ReactiveCrudRepository<Proyecto, Int> {

    fun findByEstadoProyecto(estadoProyecto: Boolean): Flux<Proyecto>
}
