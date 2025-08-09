package com.sicon.tba.repository;

import com.sicon.tba.models.TbaErrores;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaErroresRepository extends ReactiveCrudRepository<TbaErrores, Integer> {
}
