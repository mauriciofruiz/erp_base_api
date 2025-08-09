package com.sicon.tba.repository.custom;

import com.sicon.tba.models.TbaRol;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TbaRolRepositoryCustom {
    Flux<TbaRol> getBySearchParams(
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset
    );

    Mono<Long> getCountBySearchParams();
}
