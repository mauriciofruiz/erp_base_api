package com.sicon.tba.repository;

import com.sicon.tba.models.TbaCargo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaCargoRepository extends ReactiveCrudRepository<TbaCargo, Integer> {
}
