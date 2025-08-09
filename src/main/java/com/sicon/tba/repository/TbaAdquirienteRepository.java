package com.sicon.tba.repository;

import com.sicon.tba.models.TbaAdquiriente;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TbaAdquirienteRepository extends ReactiveCrudRepository<TbaAdquiriente, Integer> {
    Flux<TbaAdquiriente> findAllByEstadoAdquiriente(Boolean estadoAdquiriente);
}
