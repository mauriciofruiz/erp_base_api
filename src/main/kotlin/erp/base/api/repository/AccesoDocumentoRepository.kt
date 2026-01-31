package erp.base.api.repository

import erp.base.api.models.AccesoDocumento
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface AccesoDocumentoRepository : ReactiveCrudRepository<AccesoDocumento, Int> {

    fun findByIdDocumentoAndIdTipoDocumentoAndNameUsuario(
        documentId: Int,
        documentTypeId: Int,
        nameUsuario: String
    ): Mono<AccesoDocumento>

    fun findByIdDocumentoAndIdTipoDocumento(documentId: Int, documentTypeId: Int): Mono<AccesoDocumento>

    fun findByNameUsuario(nameUsuario: String): Flux<AccesoDocumento>
}
