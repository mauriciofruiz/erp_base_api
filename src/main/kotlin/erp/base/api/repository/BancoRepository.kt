package erp.base.api.repository

import erp.base.api.models.Banco
import erp.base.api.repository.custom.BancoRepositoryCustom
import org.springframework.data.domain.Pageable
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface BancoRepository : ReactiveCrudRepository<Banco, Int>, BancoRepositoryCustom {

    fun findAllBy(pageable: Pageable): Flux<Banco>
    fun findByNombreBancoContaining(nombreBanco: String): Flux<Banco>
    fun findByNumeroCuentaBancoContaining(numeroCuenta: String): Flux<Banco>
    fun countByNombreBanco(nameBank: String): Mono<Long>
    fun countByNumeroCuentaBanco(countByNumeroCuenta: String): Mono<Long>
    fun findByEstadoBanco(estadoBanco: Boolean): Flux<Banco>
    fun findByEstadoBancoAndEsCajaChicaBanco(estadoBanco: Boolean, esCajaChicaBanco: Boolean): Flux<Banco>

    @Query("SELECT MAX(ultimonumcheque_banco) FROM tba_banco where id_banco = :idBanco and estado_banco = :estadoBanco")
    fun getLastNumberCheckBank(idBanco: Int, estadoBanco: Boolean): Mono<Int?>

    fun findByIdSucursalAndEstadoBanco(idSucursal: Int, estadoBanco: Boolean): Flux<Banco>
}
