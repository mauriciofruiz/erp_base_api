package com.sicon.tba.repository;

import com.sicon.tba.models.TbaPagina;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaPaginaRepository extends ReactiveCrudRepository<TbaPagina, Integer> {
}
