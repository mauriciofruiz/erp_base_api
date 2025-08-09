package com.sicon.tba.repository.cont;

import com.sicon.tba.models.ConAsiento;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConAsientoRepository extends ReactiveCrudRepository<ConAsiento, Integer> {

}
