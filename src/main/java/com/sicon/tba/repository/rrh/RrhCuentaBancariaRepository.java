package com.sicon.tba.repository.rrh;

import com.sicon.tba.models.rrh.RrhCargaFamiliar;
import com.sicon.tba.models.rrh.RrhCuentaBancaria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RrhCuentaBancariaRepository  extends ReactiveCrudRepository<RrhCuentaBancaria, Integer> {

    Mono<Boolean> existsByNumeroCuentaBancaria(String numeroCuenta);
    Flux<RrhCuentaBancaria> findByIdEmpleado(Integer idEmpleado);
}
