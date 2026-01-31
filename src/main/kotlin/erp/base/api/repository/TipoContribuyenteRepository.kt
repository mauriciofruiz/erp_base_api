package erp.base.api.repository

import erp.base.api.models.TipoContribuyente
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface TipoContribuyenteRepository : ReactiveCrudRepository<TipoContribuyente, String> {

    @Query("INSERT INTO TRB_TIPOCONTRIBUYENTE (ID_TIPOCONTRIBUYENTE, NOMBRE_TIPOCONTRIBUYENTE, COD_TIPOCONTRIBUYENTE) VALUES (:idTipoContribuyente, :nombreTipoContribuyente, :codigoTipoContribuyente)")
    fun createContributorType(idTipoContribuyente: String, nombreTipoContribuyente: String, codigoTipoContribuyente: String): Mono<TipoContribuyente>
}
