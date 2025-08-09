package com.sicon.tba.repository;

import com.sicon.tba.models.TbaEstadoSRI;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaEstadoSRIRepository extends ReactiveCrudRepository<TbaEstadoSRI, Integer> {
}
