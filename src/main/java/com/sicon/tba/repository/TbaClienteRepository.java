package com.sicon.tba.repository;

import com.sicon.tba.models.TbaCliente;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TbaClienteRepository extends ReactiveCrudRepository<TbaCliente, Integer> {

    Mono<TbaCliente> findByIdPersona(Integer idPersona);
    Flux<TbaCliente> findAllBy(Pageable pageable);
    Flux<TbaCliente> findByNombreComercialClienteContaining(String nombreComercialCliente, Pageable pageable);
    Flux<TbaCliente> findByIdCategoriaCliente(Integer idCategoriaCliente, Pageable pageable);

    /*Paginacion*/
    Mono<Long> countByIdCategoriaCliente(Integer idCategoriaCliente);
    Mono<Long> countByNombreComercialCliente(String nombreComercialCliente);





}