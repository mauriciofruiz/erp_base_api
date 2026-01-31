package erp.base.api.repository

import erp.base.api.models.CategoriaSucursal
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface CategoriaSucursalRepository : ReactiveCrudRepository<CategoriaSucursal, String> {

    fun findByNombreCategoriaSucursal(nombreCategoriaSucursal: String): Flux<CategoriaSucursal>

    @Query(
        "INSERT INTO TBA_CATEGORIASUCURSAL(ID_CATEGORIASUCURSAL, NOMBRE_CATEGORIASUCURSAL, DESCRIPCION_CATEGORIASUCURSAL) VALUES (:idCategoriaSucursal, :nombreCategoriaSucursal, :descripcionCategoriaSucursal)"
    )
    fun insert(
        idCategoriaSucursal: String,
        nombreCategoriaSucursal: String,
        descripcionCategoriaSucursal: String
    ): Mono<CategoriaSucursal>
}
