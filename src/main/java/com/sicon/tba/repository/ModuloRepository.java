package com.sicon.tba.repository;

import com.sicon.tba.models.Modulo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuloRepository extends ReactiveCrudRepository<Modulo, Integer> {
}
