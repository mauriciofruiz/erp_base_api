package com.sicon.tba.repository.rrh;

import com.sicon.tba.models.rrh.RrhDiscapacidad;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RrhDiscapacidadRepository extends ReactiveCrudRepository<RrhDiscapacidad, Integer> {
}
