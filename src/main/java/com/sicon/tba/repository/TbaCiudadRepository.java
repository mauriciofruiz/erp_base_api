package com.sicon.tba.repository;

import com.sicon.tba.dto.TbaCiudadDto;
import com.sicon.tba.models.TbaCiudad;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TbaCiudadRepository extends ReactiveCrudRepository<TbaCiudad, Integer> {
    Flux<TbaCiudad> findByIdProvincia(String idProvincia);

    Flux<TbaCiudad> findByNombreCiudadContaining(String nombreCiudad);

    Flux<TbaCiudad> findByCodigoCiudad(String codigoCiudad);


    @Query(" SELECT TC.NOMBRE_CIUDAD, TP.NOMBRE_PROVINCIA " +
            " FROM TBA_CIUDAD TC JOIN TBA_PARROQUIA P on TC.ID_CIUDAD = P.ID_CIUDAD" +
            " JOIN TBA_PROVINCIA TP on TP.ID_PROVINCIA = TC.ID_PROVINCIA" +
            " WHERE P.ID_PARROQUIA =:idParroquia")
    Mono<TbaCiudadDto> getCityByIdParish(Integer idParroquia);

}
