package erp.base.api.repository

import erp.base.api.dto.ParroquiaDto
import erp.base.api.models.Parroquia
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface ParroquiaRepository : ReactiveCrudRepository<Parroquia, Int> {

    @Query(" SELECT  TP.ID_PARROQUIA, TP.ID_CIUDAD, TP.NOMBRE_PARROQUIA FROM PARROQUIA TP" +
            " WHERE TP.NOMBRE_PARROQUIA LIKE CONCAT('%', :nombreParroquia, '%')")
    fun findParishByName(nombreParroquia: String): Flux<ParroquiaDto>

    fun findByIdCiudad(idCiudad: Int): Flux<Parroquia>
}
