package erp.base.api.repository.custom;

import erp.base.api.dto.DocumentoDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DocumentoRepositoryCustom {
    Flux<DocumentoDto> getBySearchParams(
            String documentNumber,
            String documentType,
            String name,
            String identification,
            String accesKey,
            Boolean isAuthorized,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset
    );

    Mono<Long> getCountBySearchParams(
            String documentNumber,
            String documentType,
            String name,
            String identification,
            String accesKey,
            Boolean isAuthorized
    );
}
