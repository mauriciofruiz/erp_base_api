package erp.base.api.repository

import erp.base.api.models.FormaPagoCobro
import erp.base.api.models.TipoContribuyente
import erp.base.api.repository.custom.FormaPagoRepositoryCustom
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Repository
interface FormaPagoCobroRepository : ReactiveCrudRepository<FormaPagoCobro, String>, FormaPagoRepositoryCustom {

    @Query(
        "INSERT INTO TBA_FORMAPAGOCOBRO (ID_FORMAPAGOCOBRO, NOMBRE_FORMAPAGOCOBRO, ALIAS_FORMAPAGOCOBRO, DESCUENTO_FORMAPAGOCOBRO, COD_FORMAPAGOCOBRO, ESTADO_FORMAPAGOCOBRO) VALUES (:idFormaPagoCobro, :nombreFormaPagoCobro, :aliasFormaPagoCobro, :descuentoFormaPagoCobro, :codigoFormaPagoCobro, :estadoFormaPagoCobro)"
    )
    fun createPaymentCollectionMethod(
        idFormaPagoCobro: String,
        nombreFormaPagoCobro: String,
        aliasFormaPagoCobro: String,
        descuentoFormaPagoCobro: BigDecimal,
        codigoFormaPagoCobro: String,
        estadoFormaPagoCobro: Boolean
    ): Mono<TipoContribuyente>

    fun findByEstadoFormaPagoCobro(estadoFormaPagoCobro: Boolean): Flux<FormaPagoCobro>
}
