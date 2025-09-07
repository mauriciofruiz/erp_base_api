package erp.base.api.repository.custom;

import erp.base.api.models.Caja;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TbaCajaRepositoryCustom {

    Flux<Caja> getBySearchParams(
            Integer branchId,
            String name,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset
    );

    Mono<Long> getCountBySearchParams(
            Integer branchId,
            String name
    );
}
