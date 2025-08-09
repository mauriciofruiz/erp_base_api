package com.sicon.tba.repository;

import com.sicon.tba.dto.TbaParroquiaDto;
import com.sicon.tba.models.TbaParroquia;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface TbaParroquiaRepository extends ReactiveCrudRepository<TbaParroquia, Integer> {

    @Query(" SELECT  TP.ID_PARROQUIA, TP.ID_CIUDAD, TP.NOMBRE_PARROQUIA FROM TBA_PARROQUIA TP" +
            " WHERE TP.NOMBRE_PARROQUIA LIKE CONCAT('%', :nombreParroquia, '%')")
    Flux<TbaParroquiaDto> findParishByName(String nombreParroquia);


    Flux<TbaParroquia> findByIdCiudad(Integer idCiudad);
}
