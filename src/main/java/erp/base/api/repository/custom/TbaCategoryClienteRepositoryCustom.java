package erp.base.api.repository.custom;

import erp.base.api.models.TbaCategoriaCliente;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TbaCategoryClienteRepositoryCustom {

    Flux<TbaCategoriaCliente> getBySearchParams(
            String name,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset
    );

    Mono<Long> getCountBySearchParams(
            String name
    );
}
