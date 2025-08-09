package com.sicon.tba.repository;

import com.sicon.tba.models.TbaGenero;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaGeneroRepository extends ReactiveCrudRepository<TbaGenero, Integer> {
}
