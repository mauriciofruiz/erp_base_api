package erp.base.api.repository

import erp.base.api.dto.CiudadDto
import erp.base.api.models.Ciudad
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface CiudadRepository : ReactiveCrudRepository<Ciudad, Int> {

    fun findByIdProvincia(idProvincia: String): Flux<Ciudad>
    fun findByNombreCiudadContaining(nombreCiudad: String): Flux<Ciudad>
    fun findByCodigoCiudad(codigoCiudad: String): Flux<Ciudad>

    @Query(" SELECT TC.NOMBRE_CIUDAD, TP.NOMBRE_PROVINCIA " +
            " FROM CIUDAD TC JOIN PARROQUIA P on TC.ID_CIUDAD = P.ID_CIUDAD" +
            " JOIN PROVINCIA TP on TP.ID_PROVINCIA = TC.ID_PROVINCIA" +
            " WHERE P.ID_PARROQUIA =:idParroquia")
    fun getCityByIdParish(idParroquia: Int): Mono<CiudadDto>
}
