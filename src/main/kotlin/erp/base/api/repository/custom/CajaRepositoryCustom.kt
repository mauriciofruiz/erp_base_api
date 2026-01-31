package erp.base.api.repository.custom

import erp.base.api.models.Caja
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CajaRepositoryCustom {

    fun getBySearchParams(
        branchId: Int?,
        name: String?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<Caja>

    fun getCountBySearchParams(
        branchId: Int?,
        name: String?
    ): Mono<Long>
}
