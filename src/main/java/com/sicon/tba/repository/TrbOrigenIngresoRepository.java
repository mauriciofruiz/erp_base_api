package com.sicon.tba.repository;


import com.sicon.tba.models.TrbOrigenIngreso;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrbOrigenIngresoRepository extends ReactiveCrudRepository<TrbOrigenIngreso, Integer> {
}
