package erp.base.api.repository

import erp.base.api.models.Provincia
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface ProvinciaRepository : ReactiveCrudRepository<Provincia, String> {

    fun findByIdPais(idPais: Int): Flux<Provincia>
    fun findByNombreProvinciaContaining(nombreProvincia: String): Flux<Provincia>
    fun findByCodRegionContaining(codRegion: String): Flux<Provincia>
    fun findByIdPaisAndNombreProvinciaContaining(idPais: Int, nombreProvincia: String): Flux<Provincia>
    fun findByIdPaisAndCodRegionContaining(idPais: Int, codRegion: String): Flux<Provincia>
    fun findByNombreProvinciaContainingAndCodRegionContaining(nombreProvincia: String, codRegion: String): Flux<Provincia>
    fun findByIdPaisAndNombreProvinciaContainingAndCodRegionContaining(idPais: Int, nombreProvincia: String, codRegion: String): Flux<Provincia>

    @Query("INSERT INTO TBA_PROVINCIA (ID_PROVINCIA, ID_PAIS, NOMBRE_PROVINCIA, COD_REGION) VALUES (:provinceId, :countryId, :provinceName, :codRegion)")
    fun insert(provinceId: String, countryId: Int, provinceName: String, codRegion: Int): Mono<Provincia>
}
