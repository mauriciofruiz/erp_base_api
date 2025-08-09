package com.sicon.tba.repository;

import com.sicon.tba.models.TipoComprobante;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TipoComprobanteRepository extends ReactiveCrudRepository<TipoComprobante, Integer> {
}
