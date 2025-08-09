package com.sicon.tba.repository;

import com.sicon.tba.models.TbaBloqueo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaBloqueoRepository extends ReactiveCrudRepository<TbaBloqueo, Integer> {
}
