package com.sicon.tba.repository;

import com.sicon.tba.models.TbaUnidadMedida;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaUnidadMedidaRepository extends ReactiveCrudRepository<TbaUnidadMedida, Integer> {
}
