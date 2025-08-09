package com.sicon.tba.repository;

import com.sicon.tba.models.TrbTipoPago;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrbTipoPagoRepository extends ReactiveCrudRepository<TrbTipoPago, Integer> {
}
