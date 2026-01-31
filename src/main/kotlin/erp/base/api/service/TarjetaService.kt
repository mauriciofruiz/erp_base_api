package erp.base.api.service

import erp.base.api.models.Tarjeta
import erp.base.api.repository.TarjetaRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class TarjetaService(private val tarjetaRepository: TarjetaRepository) {

    fun getAllTarjetas(): Flux<Tarjeta> =
        tarjetaRepository.findAllByEstadoTarjeta(true)
}
