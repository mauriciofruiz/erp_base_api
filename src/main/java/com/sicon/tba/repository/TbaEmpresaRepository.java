package com.sicon.tba.repository;

import com.sicon.tba.models.TbaEmpresa;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaEmpresaRepository extends ReactiveCrudRepository<TbaEmpresa, Long> {

}
