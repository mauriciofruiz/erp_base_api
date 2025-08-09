package com.sicon.tba.repository;

import com.sicon.tba.models.TbaSeg;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TbaSegRepository extends ReactiveCrudRepository<TbaSeg, Integer> {
}
