package erp.base.api.handler;


import erp.base.api.exception.CustomException;
import erp.base.api.models.AccesoDocumento;
import erp.base.api.service.AccesoDocumentoService;
import erp.base.api.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Clase que contiene los servicios de acceso de documentos
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class AccesoDocumentoHandler {

    private final AccesoDocumentoService accesoDocumentoService;

    private final ObjectValidator objectValidator;

    /**
     * Método que crea un documento y bloquear el accesso
     *
     * @param request Objeto con los datos de la petición
     * @return
     */
    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(AccesoDocumento.class).doOnNext(objectValidator::validate)
                .flatMap(accesoDocumento -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(accesoDocumentoService.conditionalSave(accesoDocumento), AccesoDocumento.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Metodo que consulta si existe un documento por su id, el tipo de documento y nombre de usuario
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> findByDocumentIdAndDocumentTypeIdAndName(ServerRequest request) {
        return request.bodyToMono(AccesoDocumento.class).doOnNext(objectValidator::validate)
                .flatMap(accesoDocumento -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(accesoDocumentoService.findByDocumentAndTypeAndName(accesoDocumento.getIdDocumento(), accesoDocumento.getIdTipoDocumento(), accesoDocumento.getNameUsuario()), Void.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que permite obtener todos lo documentos abiertos
     *
     * @param request Objeto con los datos de la petición
     * @return Objeto con todos lo documentos abiertos
     */
    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(accesoDocumentoService.findAll(), AccesoDocumento.class).onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> deleteByDocumentIdAndDocumentTypeId(ServerRequest request) {
        return request.bodyToMono(AccesoDocumento.class).doOnNext(objectValidator::validate)
                .flatMap(accesoDocumento -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(accesoDocumentoService.deleteByDocumentAndTypeAndName(accesoDocumento.getIdDocumento(), accesoDocumento.getIdTipoDocumento(), accesoDocumento.getNameUsuario()), Void.class));
    }


    /**
     * Metodo que consulta si existe un documento por su id y el tipo de documento
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> findByDocumentIdAndDocumentTypeId(ServerRequest request) {
        return request.bodyToMono(AccesoDocumento.class).doOnNext(objectValidator::validate)
                .flatMap(accesoDocumento -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(accesoDocumentoService.findByDocumentAndType(accesoDocumento.getIdDocumento(), accesoDocumento.getIdTipoDocumento()), AccesoDocumento.class)).onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
