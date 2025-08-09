package com.sicon.tba.repository;

import com.sicon.tba.models.TbaEstadoDeposito;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaEstadoDepositoRepository extends ReactiveCrudRepository<TbaEstadoDeposito, Integer> {
}
