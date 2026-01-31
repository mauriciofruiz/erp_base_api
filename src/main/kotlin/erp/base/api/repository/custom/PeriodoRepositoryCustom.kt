package erp.base.api.repository.custom

import erp.base.api.models.Periodo
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.ZonedDateTime

interface PeriodoRepositoryCustom {

    fun getBySearchParams(
        moduleId: Int?,
        startDate: ZonedDateTime?,
        endDate: ZonedDateTime?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<Periodo>

    fun getCountBySearchParams(
        moduleId: Int?,
        startDate: ZonedDateTime?,
        endDate: ZonedDateTime?
    ): Mono<Long>
}
