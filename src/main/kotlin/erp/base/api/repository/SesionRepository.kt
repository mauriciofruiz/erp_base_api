package erp.base.api.repository

import erp.base.api.models.Sesion
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface SesionRepository : ReactiveCrudRepository<Sesion, Int> {

    fun findByToken(token: String): Mono<Sesion>
    fun findByIdUsr(idUsr: Int): Mono<Sesion>
    fun existsSesionByIdUsrAndEstado(idUsr: Int, estado: Boolean): Mono<Boolean>
    fun findByIdUsrAndEstado(idUsr: Int, estado: Boolean): Mono<Sesion>
}
