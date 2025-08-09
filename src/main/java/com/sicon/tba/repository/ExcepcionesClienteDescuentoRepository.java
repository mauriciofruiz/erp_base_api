package com.sicon.tba.repository;

import com.sicon.tba.models.ExcepcionesClienteDescuento;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ExcepcionesClienteDescuentoRepository extends ReactiveCrudRepository<ExcepcionesClienteDescuento, Integer> {

    Flux<ExcepcionesClienteDescuento> findAllByIdCliente(Long idCliente);

    Flux<ExcepcionesClienteDescuento> findByIdClienteAndEstado(Long idCliente, Boolean estado, Pageable pageable);

    Mono<Long> countByIdClienteAndEstado(Long idCliente, Boolean estado);
}
