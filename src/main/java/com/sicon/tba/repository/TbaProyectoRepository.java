package com.sicon.tba.repository;

import com.sicon.tba.models.TbaProyecto;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TbaProyectoRepository extends ReactiveCrudRepository<TbaProyecto, Integer> {
    Flux<TbaProyecto> findByEstadoProyecto(Boolean estadoProyecto);
}
