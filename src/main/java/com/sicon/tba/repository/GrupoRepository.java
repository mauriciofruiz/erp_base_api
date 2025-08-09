package com.sicon.tba.repository;

import com.sicon.tba.models.InvGrupo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GrupoRepository extends ReactiveCrudRepository<InvGrupo, Integer> {

}
