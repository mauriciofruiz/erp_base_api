package erp.base.api.service;

import erp.base.api.models.AccesoDocumento;
import erp.base.api.repository.AccesoDocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase que contiene los servicios de acceso de documentos
 */
@Service
@RequiredArgsConstructor
public class AccesoDocumentoService {

    private final AccesoDocumentoRepository accesoDocumentoRepository;

    public Flux<AccesoDocumento> findAll() {
        return accesoDocumentoRepository.findAll();
    }

    public Mono<ResponseEntity<Void>> conditionalSave(AccesoDocumento accesoDocumento) {
        return accesoDocumentoRepository
                .findByIdDocumentoAndIdTipoDocumentoAndNameUsuario(accesoDocumento.getIdDocumento(), accesoDocumento.getIdTipoDocumento(), accesoDocumento.getNameUsuario())
                .map(result -> ResponseEntity.ok().<Void>build())
                .switchIfEmpty(Mono.defer(() -> save(accesoDocumento)));
    }
    public Mono<ResponseEntity<Void>> save(AccesoDocumento accesoDocumento) {
        return accesoDocumentoRepository.save(accesoDocumento)
                .map(result -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<ResponseEntity<Void>> findByDocumentAndTypeAndName(Integer documentId, Integer documentTypeId, String nameUsuario) {
        return accesoDocumentoRepository
                .findByIdDocumentoAndIdTipoDocumentoAndNameUsuario(documentId, documentTypeId, nameUsuario)
                .map(result -> ResponseEntity.ok().<Void>build())
                .switchIfEmpty(Mono.defer(() -> findByIdDocumentAndIdTipoDocumento(documentId, documentTypeId)));
    }

    private Mono<ResponseEntity<Void>> findByIdDocumentAndIdTipoDocumento(Integer documentId, Integer documentTypeId) {
        return accesoDocumentoRepository
                .findByIdDocumentoAndIdTipoDocumento(documentId, documentTypeId)
                .map(result -> ResponseEntity.notFound().<Void>build())
                .defaultIfEmpty(ResponseEntity.ok().build());
    }


    public Mono<ResponseEntity<Void>> deleteByDocumentAndTypeAndName(Integer documentId, Integer documentTypeId, String nameUsuario) {
        return accesoDocumentoRepository.findByIdDocumentoAndIdTipoDocumentoAndNameUsuario(documentId, documentTypeId, nameUsuario)
                .flatMap(accesoDocumento -> accesoDocumentoRepository.deleteById(accesoDocumento.getIdAccesoDocumento()))
                .then(Mono.just(ResponseEntity.ok().build()));

    }

    public Mono<ResponseEntity<AccesoDocumento>> findByDocumentAndType(Integer documentId, Integer documentTypeId) {
        return accesoDocumentoRepository.findByIdDocumentoAndIdTipoDocumento(documentId, documentTypeId).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
