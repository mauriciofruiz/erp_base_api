package com.sicon.tba.repository;

import com.sicon.tba.models.TbaRol;
import com.sicon.tba.repository.custom.TbaRolRepositoryCustom;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaRolRepository extends ReactiveCrudRepository<TbaRol, Integer>, TbaRolRepositoryCustom {
}
