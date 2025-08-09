package com.sicon.tba.repository;

import com.sicon.tba.models.TbaCondicionPago;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaCondicionPagoRepository extends ReactiveCrudRepository<TbaCondicionPago, Integer> {
}
