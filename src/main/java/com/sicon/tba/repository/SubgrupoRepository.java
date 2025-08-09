package com.sicon.tba.repository;

import com.sicon.tba.models.InvSubGrupo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubgrupoRepository extends ReactiveCrudRepository<InvSubGrupo, Integer> {

}
