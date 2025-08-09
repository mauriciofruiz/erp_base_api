package com.sicon.tba.repository;

import com.sicon.tba.models.TbaPais;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaPaisRepository extends ReactiveCrudRepository<TbaPais, Integer> {
}
