package erp.base.api.repository.custom

import erp.base.api.models.Banco
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface BancoRepositoryCustom {

    fun getBySearchParams(
        nameBank: String?,
        numCount: String?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<Banco>

    fun getCountBySearchParams(
        nameBank: String?,
        numCount: String?
    ): Mono<Long>
}
