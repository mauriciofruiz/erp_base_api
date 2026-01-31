package erp.base.api.repository.custom

import erp.base.api.dto.DocumentoDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface DocumentoRepositoryCustom {

    fun getBySearchParams(
        documentNumber: String?,
        documentType: String?,
        name: String?,
        identification: String?,
        accesKey: String?,
        isAuthorized: Boolean?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<DocumentoDto>

    fun getCountBySearchParams(
        documentNumber: String?,
        documentType: String?,
        name: String?,
        identification: String?,
        accesKey: String?,
        isAuthorized: Boolean?
    ): Mono<Long>
}
