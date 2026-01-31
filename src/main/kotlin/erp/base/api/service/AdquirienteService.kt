package erp.base.api.service

import erp.base.api.models.Adquiriente
import erp.base.api.repository.AdquirienteRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class AdquirienteService(private val adquirienteRepository: AdquirienteRepository) {

    fun getAllByActiveEstado(): Flux<Adquiriente> =
        adquirienteRepository.findAllByEstadoAdquiriente(true)
}
