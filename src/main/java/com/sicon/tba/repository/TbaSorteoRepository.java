package com.sicon.tba.repository;

import com.sicon.tba.models.TbaSorteo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaSorteoRepository extends ReactiveCrudRepository<TbaSorteo, Integer> {
}
