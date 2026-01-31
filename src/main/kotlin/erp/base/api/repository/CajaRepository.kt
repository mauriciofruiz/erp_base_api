package erp.base.api.repository

import erp.base.api.models.Caja
import erp.base.api.repository.custom.CajaRepositoryCustom
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface CajaRepository : ReactiveCrudRepository<Caja, Int>, CajaRepositoryCustom {

    fun findByIdSucursal(idSucursal: Int): Flux<Caja>
}
