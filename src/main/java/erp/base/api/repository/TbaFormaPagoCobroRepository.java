package erp.base.api.repository;

import erp.base.api.models.TbaFormaPagoCobro;
import erp.base.api.models.TrbTipoContribuyente;
import erp.base.api.repository.custom.TbaFormaPagoRepositoryCustom;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Repository
public interface TbaFormaPagoCobroRepository extends ReactiveCrudRepository<TbaFormaPagoCobro, String>, TbaFormaPagoRepositoryCustom {

    @Query("INSERT INTO TBA_FORMAPAGOCOBRO (ID_FORMAPAGOCOBRO, NOMBRE_FORMAPAGOCOBRO, ALIAS_FORMAPAGOCOBRO, DESCUENTO_FORMAPAGOCOBRO, COD_FORMAPAGOCOBRO, ESTADO_FORMAPAGOCOBRO) VALUES (:idFormaPagoCobro, :nombreFormaPagoCobro, :aliasFormaPagoCobro, :descuentoFormaPagoCobro, :codigoFormaPagoCobro, :estadoFormaPagoCobro)")
    Mono<TrbTipoContribuyente> createPaymentCollectionMethod(String idFormaPagoCobro, String nombreFormaPagoCobro, String aliasFormaPagoCobro, BigDecimal descuentoFormaPagoCobro, String codigoFormaPagoCobro, boolean estadoFormaPagoCobro);
    Flux<TbaFormaPagoCobro> findByEstadoFormaPagoCobro(boolean estadoFormaPagoCobro);
}
