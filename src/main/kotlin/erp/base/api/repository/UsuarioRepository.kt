package erp.base.api.repository

import erp.base.api.models.Usuario
import org.springframework.data.domain.Pageable
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface UsuarioRepository : ReactiveCrudRepository<Usuario, Int> {

    fun findByNickUsrIgnoreCase(nickUsr: String): Mono<Usuario>
    fun existsByNickUsrIgnoreCase(nickUsr: String): Mono<Boolean>
    fun existsByIdPersona(idPersona: Int): Mono<Boolean>
    fun findByIdPersona(idPersona: Int): Mono<Usuario>
    fun findAllBy(pageable: Pageable): Flux<Usuario>
    fun countByNickUsrContainingAndEstadoUsr(nickUsr: String, estadoUsr: Boolean): Mono<Long>
    fun findByNickUsrContainingAndEstadoUsr(nickUsr: String, estadoUsr: Boolean, pageable: Pageable): Flux<Usuario>
    fun countByEstadoUsr(estadoUsr: Boolean): Mono<Long>
    fun findByEstadoUsr(estadoUsr: Boolean, pageable: Pageable): Flux<Usuario>

    @Query(" SELECT DISTINCT TU.* FROM USUARIO TU RIGHT JOIN SOLICITUDCOMPRA ADQ on TU.ID_USR = ADQ.ID_USR WHERE TU.ESTADO_USR =:estado")
    fun findUsersRequestPurchase(estado: Boolean): Flux<Usuario>

    @Query(" SELECT DISTINCT TU.* FROM USUARIO TU RIGHT JOIN ORDENCOMPRA AO on TU.ID_USR = AO.ID_USR WHERE TU.ESTADO_USR =:estado")
    fun findUsersPurchaseOrder(estado: Boolean): Flux<Usuario>
}
