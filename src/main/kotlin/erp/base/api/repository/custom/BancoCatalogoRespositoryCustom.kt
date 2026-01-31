package erp.base.api.repository.custom

import erp.base.api.models.BancoCatalogo
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface BancoCatalogoRespositoryCustom {

    fun getBySearchParams(
        bankCatalogName: String?,
        bankCatalogCode: String?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<BancoCatalogo>

    fun getCountBySearchParams(
        bankCatalogName: String?,
        bankCatalogCode: String?
    ): Mono<Long>
}
