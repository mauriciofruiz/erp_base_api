package com.sicon.tba.repository;

import com.sicon.tba.models.TbaPaginaRol;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaPaginaRolRepository extends ReactiveCrudRepository<TbaPaginaRol, Integer> {
}
