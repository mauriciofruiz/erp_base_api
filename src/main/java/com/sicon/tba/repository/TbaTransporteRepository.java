package com.sicon.tba.repository;

import com.sicon.tba.models.TbaTransporte;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TbaTransporteRepository extends ReactiveCrudRepository<TbaTransporte, Integer> {

    @Query(value = "SELECT TP.*" +
            " FROM tba_transporte TP" +
            " WHERE TP.inactivo_transporte = false" +
            " AND ( :nombre IS NULL OR UPPER(TP.nombrecontacto_transporte) LIKE UPPER(CONCAT('%',:nombre,'%')))" +
            " AND ( :identificacion IS NULL OR UPPER(TP.identcontacto_transporte) LIKE UPPER(CONCAT('%',:identificacion,'%')));")
    Flux<TbaTransporte> getAllActiveTransportistas(String nombre, String identificacion);

    @Query(value = "SELECT t.*" +
            "FROM tba_transporte t " +
            "WHERE t.inactivo_transporte IS FALSE " +
            "AND t.identcontacto_transporte LIKE CONCAT('%',:ci,'%') " +
            "AND t.placa_transporte LIKE CONCAT('%',:placa,'%') " +
            "LIMIT 1")
    Mono<TbaTransporte> findByIdentificacionContactoAndPlacaTransporte(String ci, String placa);

}
