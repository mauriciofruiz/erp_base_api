package com.sicon.tba.repository.custom;

import com.sicon.tba.models.TbaFormaPagoCobro;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TbaFormaPagoRepositoryCustom {
    Flux<TbaFormaPagoCobro> getBySearchParams(
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset
    );

    Mono<Long> getCountBySearchParams();
}
