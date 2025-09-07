package erp.base.api.repository.custom;

import erp.base.api.models.TbaBanco;
import erp.base.api.models.TbaBancoCatalogo;
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
