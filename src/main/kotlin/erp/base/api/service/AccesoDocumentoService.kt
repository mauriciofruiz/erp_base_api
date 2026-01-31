package erp.base.api.service

import erp.base.api.models.AccesoDocumento
import erp.base.api.repository.AccesoDocumentoRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class AccesoDocumentoService(private val accesoDocumentoRepository: AccesoDocumentoRepository) {

    fun findAll(): Flux<AccesoDocumento> =
        accesoDocumentoRepository.findAll()

    fun conditionalSave(accesoDocumento: AccesoDocumento): Mono<ResponseEntity<Void>> =
        accesoDocumentoRepository
            .findByIdDocumentoAndIdTipoDocumentoAndNameUsuario(
                accesoDocumento.idDocumento,
                accesoDocumento.idTipoDocumento,
                accesoDocumento.nameUsuario
            )
            .map { ResponseEntity.ok().build<Void>() }
            .switchIfEmpty(Mono.defer { save(accesoDocumento) })

    fun save(accesoDocumento: AccesoDocumento): Mono<ResponseEntity<Void>> =
        accesoDocumentoRepository.save(accesoDocumento)
            .map { ResponseEntity.ok().build<Void>() }
            .defaultIfEmpty(ResponseEntity.notFound().build())

    fun findByDocumentAndTypeAndName(documentId: Int, documentTypeId: Int, nameUsuario: String): Mono<ResponseEntity<Void>> =
        accesoDocumentoRepository
            .findByIdDocumentoAndIdTipoDocumentoAndNameUsuario(documentId, documentTypeId, nameUsuario)
            .map { ResponseEntity.ok().build<Void>() }
            .switchIfEmpty(Mono.defer { findByIdDocumentAndIdTipoDocumento(documentId, documentTypeId) })

    private fun findByIdDocumentAndIdTipoDocumento(documentId: Int, documentTypeId: Int): Mono<ResponseEntity<Void>> =
        accesoDocumentoRepository
            .findByIdDocumentoAndIdTipoDocumento(documentId, documentTypeId)
            .map { ResponseEntity.notFound().build<Void>() }
            .defaultIfEmpty(ResponseEntity.ok().build())

    fun deleteByDocumentAndTypeAndName(documentId: Int, documentTypeId: Int, nameUsuario: String): Mono<ResponseEntity<Void>> =
        accesoDocumentoRepository.findByIdDocumentoAndIdTipoDocumentoAndNameUsuario(documentId, documentTypeId, nameUsuario)
            .flatMap { accesoDocumentoRepository.deleteById(it.idAccesoDocumento) }
            .then(Mono.just(ResponseEntity.ok().build()))

    fun findByDocumentAndType(documentId: Int, documentTypeId: Int): Mono<ResponseEntity<AccesoDocumento>> =
        accesoDocumentoRepository.findByIdDocumentoAndIdTipoDocumento(documentId, documentTypeId)
            .map { ResponseEntity.ok().body(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
}
