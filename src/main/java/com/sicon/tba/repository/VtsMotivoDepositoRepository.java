package com.sicon.tba.repository;

import com.sicon.tba.models.VtsMotivoDeposito;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VtsMotivoDepositoRepository extends ReactiveCrudRepository<VtsMotivoDeposito, Integer> {
}
