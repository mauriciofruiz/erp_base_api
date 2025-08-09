package com.sicon.tba.repository;

import com.sicon.tba.models.TbaVehiculoEmpresa;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaVehiculoEmpresaRepository extends ReactiveCrudRepository<TbaVehiculoEmpresa, Integer> {
}
