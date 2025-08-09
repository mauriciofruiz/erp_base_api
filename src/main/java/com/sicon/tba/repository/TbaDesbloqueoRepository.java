package com.sicon.tba.repository;

import com.sicon.tba.models.TbaDesbloqueo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaDesbloqueoRepository extends ReactiveCrudRepository<TbaDesbloqueo, Integer> {
}
