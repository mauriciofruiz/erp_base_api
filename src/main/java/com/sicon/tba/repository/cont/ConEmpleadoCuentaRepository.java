package com.sicon.tba.repository.cont;

import com.sicon.tba.models.ConEmpleadoCuenta;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ConEmpleadoCuentaRepository extends ReactiveCrudRepository<ConEmpleadoCuenta, Integer> {

    Flux<ConEmpleadoCuenta> findByIdEmpleado(Integer idEmpleado);
}
