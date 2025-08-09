package com.sicon.tba.repository;

import com.sicon.tba.models.TbaCupon;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaCuponRepository extends ReactiveCrudRepository<TbaCupon, Integer> {
}
