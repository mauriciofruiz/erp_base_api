package com.sicon.tba.repository;

import com.sicon.tba.models.TbaPagoFacturaProveedor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaPagoFacturaProveedorRepository extends ReactiveCrudRepository<TbaPagoFacturaProveedor, Integer> {
}
