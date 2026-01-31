package erp.base.api.service

import erp.base.api.models.Proyecto
import erp.base.api.repository.ProyectoRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class ProyectoService(private val tbaProyectoRepository: ProyectoRepository) {

    fun findAllProjectsActive(): Flux<Proyecto> =
        tbaProyectoRepository.findByEstadoProyecto(true)
}
