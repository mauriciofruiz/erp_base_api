package com.sicon.tba.repository;

import com.sicon.tba.models.TarifaIva;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TarifaIvaRepository extends ReactiveCrudRepository<TarifaIva, Integer> {
    Flux<TarifaIva> findByEstadoTarifaIva(Boolean estadoTarifaIva);
}
