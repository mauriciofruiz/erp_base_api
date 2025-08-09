package com.sicon.tba.repository;

import com.sicon.tba.models.TbaTipoProveedor;
import com.sicon.tba.models.TrbTipoContribuyente;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TbaTipoProveedorRepository extends ReactiveCrudRepository<TbaTipoProveedor, Integer> {

}
