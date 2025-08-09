package com.sicon.tba.repository;

import com.sicon.tba.models.TbaTipoCuentaBancaria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaTipoCuentaBancariaRepository extends ReactiveCrudRepository<TbaTipoCuentaBancaria, Integer> {
}
