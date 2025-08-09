package com.sicon.tba.repository;

import com.sicon.tba.models.TbaProvincia;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TbaProvinciaRepository extends ReactiveCrudRepository<TbaProvincia, String> {

    Flux<TbaProvincia> findByIdPais(Integer idPais);

    Flux<TbaProvincia> findByNombreProvinciaContaining(String nombreProvincia);

    Flux<TbaProvincia> findByCodRegionContaining(String codRegion);

    Flux<TbaProvincia> findByIdPaisAndNombreProvinciaContaining(Integer idPais, String nombreProvincia);

    Flux<TbaProvincia> findByIdPaisAndCodRegionContaining(Integer idPais, String codRegion);

    Flux<TbaProvincia> findByNombreProvinciaContainingAndCodRegionContaining(String nombreProvincia, String codRegion);

    Flux<TbaProvincia> findByIdPaisAndNombreProvinciaContainingAndCodRegionContaining(Integer idPais, String nombreProvincia, String codRegion);

    @Query("INSERT INTO TBA_PROVINCIA (ID_PROVINCIA, ID_PAIS, NOMBRE_PROVINCIA, COD_REGION) VALUES (:provinceId, :countryId, :provinceName, :codRegion)")
    Mono<TbaProvincia> insert(String provinceId,  Integer countryId, String provinceName, Integer codRegion);
}
