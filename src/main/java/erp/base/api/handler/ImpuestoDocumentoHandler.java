package erp.base.api.handler;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.exception.CustomException;
import erp.base.api.models.ImpuestoDocumento;
import erp.base.api.service.ImpuestoDocumentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Log4j2
public class ImpuestoDocumentoHandler {
    private final ImpuestoDocumentoService impuestoDocumentoService;

    /**
     * Este método se utiliza para encontrar documentos fiscales por su ID de documento, ID de tipo de documento o ID de comprobante.
     * Primero intenta analizar estos ID a partir de los parámetros de consulta de la solicitud.
     * Si está presente el ID del documento, utiliza el servicio impuestoDocumentoService para encontrar el documento fiscal por el ID del documento.
     * Si el ID del documento no está presente pero sí lo están tanto el ID del tipo de documento como el ID del comprobante, utiliza el servicio impuestoDocumentoService para encontrar el documento fiscal por el ID del tipo de documento y el ID del comprobante.
     * Si ninguno de estos ID está presente, devuelve un error con un estado BAD_REQUEST y un mensaje genérico de parámetros faltantes.
     *
     * @param request La solicitud del servidor que contiene los parámetros de consulta.
     * @return Un Mono de la respuesta del servidor, que puede ser el documento fiscal encontrado o un error.
     */
    public Mono<ServerResponse> findImpuestosByDocumento(ServerRequest request) {
        Optional<Long> idDocumento = request.queryParam("idDocumento").map(Long::parseLong);
        Optional<Integer> idTipoDocumento = request.queryParam("idTipoDocumento").map(Integer::parseInt);
        Optional<Integer> idVoucher = request.queryParam("idVoucher").map(Integer::parseInt);

        if (idDocumento.isPresent()) {
            return buildResponse(impuestoDocumentoService.impuestoDocumentoByIdDocumento(idDocumento.get()), ImpuestoDocumento.class);
        } else if (idTipoDocumento.isPresent() && idVoucher.isPresent()) {
            return buildResponse(impuestoDocumentoService.impuestoDocumentoByIdTipoDocumentoAndIdVoucher(idTipoDocumento.get(), idVoucher.get()), ImpuestoDocumento.class);
        } else {
            return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, ErrorMessage.GENERIC_MISSING_PARAMETERS));
        }
    }

    private <T> Mono<ServerResponse> buildResponse(Flux<T> mono, Class<T> clazz) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mono.onErrorMap(this::handleError), clazz);
    }

    private Throwable handleError(Throwable e) {
        log.error(e.getMessage());
        return new CustomException(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
    }

}



