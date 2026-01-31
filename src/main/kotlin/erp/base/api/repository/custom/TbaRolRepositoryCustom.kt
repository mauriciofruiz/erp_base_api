package erp.base.api.repository.custom

import erp.base.api.models.TbaRol
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface TbaRolRepositoryCustom {

    fun getBySearchParams(
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<TbaRol>

    fun getCountBySearchParams(): Mono<Long>
}
