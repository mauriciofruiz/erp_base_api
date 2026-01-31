package erp.base.api.service

import erp.base.api.dto.DocumentoDto
import erp.base.api.repository.DocumentoRepository
import erp.base.api.utils.Paginacion
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class DocumentoService(private val documentRepository: DocumentoRepository) {

    fun getRequestBySearchParams(
        documentNumber: String?,
        documentType: String?,
        name: String?,
        identification: String?,
        accesKey: String?,
        isAuthorized: Boolean?,
        page: Int,
        limit: Int,
        sortDirection: String?,
        sortfield: String?
    ): Mono<Paginacion<DocumentoDto>> {
        val documentoDtoFlux = documentRepository.getBySearchParams(
            documentNumber, documentType, name, identification, accesKey, isAuthorized,
            sortfield, sortDirection, limit, (page - 1) * limit
        )
        val total = documentRepository.getCountBySearchParams(
            documentNumber, documentType, name, identification, accesKey, isAuthorized
        )
        return total.flatMap { totalCount ->
            documentoDtoFlux.collectList().map { documentoList ->
                Paginacion(documentoList, page, limit, totalCount.toInt())
            }
        }
    }
}
