package erp.base.api.repository

import erp.base.api.models.TipoContribuyente
import erp.base.api.models.TipoIdentificacionFiscal
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface TipoIdentificacionFiscalRepository : ReactiveCrudRepository<TipoIdentificacionFiscal, String> {

    @Query("INSERT INTO TRB_TIPOIDENTIFICACIONFISCAL (ID_TIPOIDFISCAL, TIPOTRANSACCION_TIPOIDFISCAL, TIPOIDENTIFICACION_TIPOIDFISCAL) VALUES (:idTipoIdFiscal, :tipoTransaccionTipoIdFiscal, :tipoIdentificacionTipoIdFiscal)")
    fun createFiscalIdentificationType(idTipoIdFiscal: String, tipoTransaccionTipoIdFiscal: String, tipoIdentificacionTipoIdFiscal: String): Mono<TipoContribuyente>
}
