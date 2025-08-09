package com.sicon.tba.repository;

import com.sicon.tba.models.TrbIdentificacionFiscal;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TrbIdentificacionFiscalRepository extends ReactiveCrudRepository<TrbIdentificacionFiscal, Integer> {
    Mono<TrbIdentificacionFiscal> findByCodIdentificacionFiscal(String codIdentificacionFiscal);

}
