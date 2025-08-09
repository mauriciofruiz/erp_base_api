package com.sicon.tba.repository;

import com.sicon.tba.models.TbaEstadoCivil;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaEstadoCivilRepository extends ReactiveCrudRepository<TbaEstadoCivil, Integer> {
}
