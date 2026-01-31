package erp.base.api.repository.custom

import erp.base.api.models.CategoriaCliente
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CategoryClienteRepositoryCustom {

    fun getBySearchParams(
        name: String?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<CategoriaCliente>

    fun getCountBySearchParams(name: String?): Mono<Long>
}
