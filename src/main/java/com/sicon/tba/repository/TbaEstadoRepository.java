package com.sicon.tba.repository;

import com.sicon.tba.models.TbaEstado;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaEstadoRepository extends ReactiveCrudRepository<TbaEstado, Integer>  {
}
