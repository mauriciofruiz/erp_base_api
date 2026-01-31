package erp.base.api.repository.custom

import erp.base.api.models.FormaPagoCobro
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface FormaPagoRepositoryCustom {

    fun getBySearchParams(
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<FormaPagoCobro>

    fun getCountBySearchParams(): Mono<Long>
}
