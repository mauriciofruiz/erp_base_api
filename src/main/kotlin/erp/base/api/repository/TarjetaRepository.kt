package erp.base.api.repository

import erp.base.api.models.Tarjeta
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface TarjetaRepository : ReactiveCrudRepository<Tarjeta, Int> {

    fun findAllByEstadoTarjeta(estadoTarjeta: Boolean): Flux<Tarjeta>
}
