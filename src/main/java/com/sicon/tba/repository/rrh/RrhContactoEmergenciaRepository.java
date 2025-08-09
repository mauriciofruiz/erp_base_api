package com.sicon.tba.repository.rrh;

import com.sicon.tba.models.rrh.RrhContactoEmergencia;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RrhContactoEmergenciaRepository extends ReactiveCrudRepository<RrhContactoEmergencia, Integer> {

     Flux<RrhContactoEmergencia> findByIdEmpleado(Integer idEmpleado) ;

}
