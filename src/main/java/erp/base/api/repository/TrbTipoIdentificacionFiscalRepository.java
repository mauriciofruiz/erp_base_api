package erp.base.api.repository;


import erp.base.api.models.TrbTipoContribuyente;
import erp.base.api.models.TrbTipoIdentificacionFiscal;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TrbTipoIdentificacionFiscalRepository extends ReactiveCrudRepository<TrbTipoIdentificacionFiscal, String> {

    @Query("INSERT INTO TRB_TIPOIDENTIFICACIONFISCAL (ID_TIPOIDFISCAL, TIPOTRANSACCION_TIPOIDFISCAL, TIPOIDENTIFICACION_TIPOIDFISCAL) VALUES (:idTipoIdFiscal, :tipoTransaccionTipoIdFiscal, :tipoIdentificacionTipoIdFiscal)")
    Mono<TrbTipoContribuyente> createFiscalIdentificationType(String idTipoIdFiscal, String tipoTransaccionTipoIdFiscal, String tipoIdentificacionTipoIdFiscal);

}
