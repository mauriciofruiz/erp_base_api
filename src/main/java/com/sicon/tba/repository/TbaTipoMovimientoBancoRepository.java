package com.sicon.tba.repository;

import com.sicon.tba.models.TbaTipoMovimientoBanco;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TbaTipoMovimientoBancoRepository extends ReactiveCrudRepository<TbaTipoMovimientoBanco, Integer> {
}
