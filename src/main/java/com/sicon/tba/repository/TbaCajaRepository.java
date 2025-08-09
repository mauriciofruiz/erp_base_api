package com.sicon.tba.repository;

import com.sicon.tba.models.Caja;
import com.sicon.tba.models.TbaPersona;
import com.sicon.tba.repository.custom.TbaCajaRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TbaCajaRepository extends ReactiveCrudRepository<Caja, Integer>, TbaCajaRepositoryCustom {

    Flux<Caja> findByIdSucursal(Integer idSucursal);
}
