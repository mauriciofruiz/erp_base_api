package com.sicon.tba.repository;

import com.sicon.tba.models.TbaTarjeta;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TbaTarjetaRepository extends ReactiveCrudRepository<TbaTarjeta, Integer> {
    Flux<TbaTarjeta> findAllByEstadoTarjeta(Boolean estadoTarjeta);
}
