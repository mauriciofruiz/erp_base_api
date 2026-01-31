package erp.base.api.repository

import erp.base.api.models.BancoCatalogo
import erp.base.api.repository.custom.BancoCatalogoRespositoryCustom
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface BancoCatalogoRepository : ReactiveCrudRepository<BancoCatalogo, Int>, BancoCatalogoRespositoryCustom {

    fun findAllByEstadoBanco(estadoBanco: Boolean): Flux<BancoCatalogo>
}
