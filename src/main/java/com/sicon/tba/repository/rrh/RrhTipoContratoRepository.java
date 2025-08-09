package com.sicon.tba.repository.rrh;


import com.sicon.tba.models.rrh.RrhTipoContrato;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RrhTipoContratoRepository extends ReactiveCrudRepository<RrhTipoContrato, Integer> {
}
