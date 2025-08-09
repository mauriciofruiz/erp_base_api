package com.sicon.tba.repository;

import com.sicon.tba.models.TbaBancoCatalogo;
import com.sicon.tba.repository.custom.TbaBancoCatalogoRespositoryCustom;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TbaBancoCatalogoRepository extends ReactiveCrudRepository<TbaBancoCatalogo, Integer>, TbaBancoCatalogoRespositoryCustom {

    Flux<TbaBancoCatalogo> findAllByEstadoBanco(Boolean estadoBanco);

}
