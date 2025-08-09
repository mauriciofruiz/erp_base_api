package com.sicon.tba.repository;

import com.sicon.tba.models.Sucursal;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TbaSucursalRepository extends ReactiveCrudRepository<Sucursal, Integer> {

    Flux<Sucursal> findByIdCiudad(Integer idCiudad);

    Flux<Sucursal> findByIdRegion(Integer idRegion);

    Flux<Sucursal> findByIdCategoriaSucursal(String idCategoriaSucursal);

    Flux<Sucursal> findByNombreSucursalContaining(String nombreSucursal);

    Flux<Sucursal> findByNemonicoSucursal(String nemonicoSucursal);

    Flux<Sucursal> findByEsOutletSucursal(Boolean esOutletSucursal);

    Flux<Sucursal> findByEstadoSucursal(Boolean estadoSucursal);

    Mono<Sucursal> findTopByOrderByIdSucursalDesc();
}
