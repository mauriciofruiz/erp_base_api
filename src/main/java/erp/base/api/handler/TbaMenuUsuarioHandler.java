package erp.base.api.handler;

import erp.base.api.dto.TbaMenuUsuarioDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TbaMenuUsuario;
import erp.base.api.service.TbaMenuUsuarioService;
import erp.base.api.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Clase que contiene los metodos para el manejo de la tabla TBA_MENU_USUARIO
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class TbaMenuUsuarioHandler {

    private final TbaMenuUsuarioService tbaMenuUsuarioService;

    private final ObjectValidator objectValidator;

    /**
     * Método que permite crear un registro en la tabla TBA_MENU_USUARIO
     *
     * @param request Objeto que contiene los datos del request enviado
     * @return Objeto Mono de TbaMenuUsuario
     */
    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<TbaMenuUsuarioDto> tbaMenuUsuarioDtoMono = request.bodyToMono(TbaMenuUsuarioDto.class).doOnNext(objectValidator::validate).log();
        return tbaMenuUsuarioDtoMono.flatMap(tbaMenuUsuarioDto ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                .body(tbaMenuUsuarioService.createUserMenu(tbaMenuUsuarioDto), TbaMenuUsuario.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que permite actualizar un registro en la tabla TBA_MENU_USUARIO
     *
     * @param request Objeto que contiene los datos del request enviado
     * @return Objeto Mono de Boolean
     */
    public Mono<ServerResponse> update(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<TbaMenuUsuarioDto> tbaMenuUsuarioDtoMono = request.bodyToMono(TbaMenuUsuarioDto.class).doOnNext(objectValidator::validate).log();
        return tbaMenuUsuarioDtoMono.flatMap(tbaMenuUsuarioDto ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                .body(tbaMenuUsuarioService.updateUserMenu(id, tbaMenuUsuarioDto), TbaMenuUsuario.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que permite actualizar masivamente los menus de un usuario
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> updateByList(ServerRequest request) {
        Mono<List<TbaMenuUsuario>> menuUsuarioListMono = request.bodyToFlux(TbaMenuUsuario.class).collectList();
        return menuUsuarioListMono.flatMap(menuUsuarioDtoList ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                .body(tbaMenuUsuarioService.updateUserMenuByList(menuUsuarioDtoList), TbaMenuUsuario.class)
                )
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que permite eliminar un registro en la tabla TBA_MENU_USUARIO
     *
     * @param request Objeto que contiene los datos del request enviado
     * @return Objeto Mono de Boolean
     */
    public Mono<ServerResponse> getById(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(tbaMenuUsuarioService.getUserMenuById(id), TbaMenuUsuario.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que permite consultar los registros según los parámetros enviados
     *
     * @param request Objeto que contiene los datos del request enviado
     * @return Objeto Mono de Boolean
     */
    public Mono<ServerResponse> getByRequestParams(ServerRequest request) {
        String userIdParam = request.queryParam("userId").orElse(null);
        String menuIdParam = request.queryParam("menuId").orElse(null);

        Integer userId = userIdParam != null ? Integer.valueOf(userIdParam) : null;
        Integer menuId = menuIdParam != null ? Integer.valueOf(menuIdParam) : null;
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(tbaMenuUsuarioService.getUserMenusByParams(menuId, userId), TbaMenuUsuario.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
