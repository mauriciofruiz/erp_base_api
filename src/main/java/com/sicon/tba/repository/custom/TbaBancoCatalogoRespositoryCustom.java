package com.sicon.tba.repository.custom;

import com.sicon.tba.models.TbaBanco;
import com.sicon.tba.models.TbaBancoCatalogo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TbaBancoCatalogoRespositoryCustom {
    Flux<TbaBancoCatalogo> getBySearchParams(
            String bankCatalogName,
            String bankCatalogCode,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset
    );

    Mono<Long> getCountBySearchParams(
            String bankCatalogName,
            String bankCatalogCode
    );
}
