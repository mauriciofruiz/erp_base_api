package com.sicon.tba.repository;

import com.sicon.tba.models.TbaTipoVehiculo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaTipoVehiculoRepository extends ReactiveCrudRepository<TbaTipoVehiculo, Integer>{
}
