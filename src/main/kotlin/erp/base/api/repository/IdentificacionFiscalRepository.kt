package erp.base.api.repository

import erp.base.api.models.IdentificacionFiscal
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface IdentificacionFiscalRepository : ReactiveCrudRepository<IdentificacionFiscal, Int> {

    fun findByCodIdentificacionFiscal(codIdentificacionFiscal: String): Mono<IdentificacionFiscal>
}
