package erp.base.api.repository

import erp.base.api.models.TarifaIva
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface TarifaIvaRepository : ReactiveCrudRepository<TarifaIva, Int> {

    fun findByEstadoTarifaIva(estadoTarifaIva: Boolean): Flux<TarifaIva>
}
