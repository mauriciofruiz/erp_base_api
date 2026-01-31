package erp.base.api.repository

import erp.base.api.models.Sucursal
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface SucursalRepository : ReactiveCrudRepository<Sucursal, Int> {

    fun findByIdCiudad(idCiudad: Int): Flux<Sucursal>
    fun findByIdRegion(idRegion: Int): Flux<Sucursal>
    fun findByIdCategoriaSucursal(idCategoriaSucursal: String): Flux<Sucursal>
    fun findByNombreSucursalContaining(nombreSucursal: String): Flux<Sucursal>
    fun findByNemonicoSucursal(nemonicoSucursal: String): Flux<Sucursal>
    fun findByEsOutletSucursal(esOutletSucursal: Boolean): Flux<Sucursal>
    fun findByEstadoSucursal(estadoSucursal: Boolean): Flux<Sucursal>
    fun findTopByOrderByIdSucursalDesc(): Mono<Sucursal>
}
