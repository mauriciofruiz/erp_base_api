package com.sicon.tba.repository;

import com.sicon.tba.models.TbaParametro;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaParametroRepository extends ReactiveCrudRepository<TbaParametro, Integer> {
}
