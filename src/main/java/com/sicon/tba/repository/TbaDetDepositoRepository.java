package com.sicon.tba.repository;

import com.sicon.tba.models.TbaDetDeposito;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaDetDepositoRepository extends ReactiveCrudRepository<TbaDetDeposito, Integer> {
}
