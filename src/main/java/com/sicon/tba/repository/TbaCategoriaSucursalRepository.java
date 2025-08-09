package com.sicon.tba.repository;

import com.sicon.tba.models.TbaCategoriaSucursal;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TbaCategoriaSucursalRepository extends ReactiveCrudRepository<TbaCategoriaSucursal, String> {

    Flux<TbaCategoriaSucursal> findByNombreCategoriaSucursal(String nombreCategoriaSucursal);

    @Query("INSERT INTO TBA_CATEGORIASUCURSAL(ID_CATEGORIASUCURSAL, NOMBRE_CATEGORIASUCURSAL, DESCRIPCION_CATEGORIASUCURSAL) VALUES (:idCategoriaSucursal, :nombreCategoriaSucursal, :descripcionCategoriaSucursal)")
    Mono<TbaCategoriaSucursal> insert(String idCategoriaSucursal, String nombreCategoriaSucursal, String descripcionCategoriaSucursal);

}
