package com.sicon.tba.repository;

import com.sicon.tba.models.InvProducto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends ReactiveCrudRepository<InvProducto, Integer> {

}
