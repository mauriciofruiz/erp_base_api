package com.sicon.tba.repository.custom;

import com.sicon.tba.models.Periodo;
import com.sicon.tba.models.TbaBanco;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

public interface TbaBancoRepositoryCustom {
    Flux<TbaBanco> getBySearchParams(
            String nameBank,
            String numCount,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset
    );

    Mono<Long> getCountBySearchParams(
            String nameBank,
            String numCount
    );
}
