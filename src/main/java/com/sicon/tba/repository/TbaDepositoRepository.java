package com.sicon.tba.repository;

import com.sicon.tba.models.TbaDeposito;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaDepositoRepository extends ReactiveCrudRepository<TbaDeposito, Integer> {
}
