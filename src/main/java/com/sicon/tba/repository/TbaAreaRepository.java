package com.sicon.tba.repository;

import com.sicon.tba.models.TbaArea;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaAreaRepository extends ReactiveCrudRepository<TbaArea, Integer> {
}
