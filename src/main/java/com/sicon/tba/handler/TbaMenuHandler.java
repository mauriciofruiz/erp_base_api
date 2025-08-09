package com.sicon.tba.handler;

import com.sicon.tba.dto.TbaMenuUsuarioResponseDto;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.TbaMenu;
import com.sicon.tba.models.TbaRol;
import com.sicon.tba.service.TbaMenuService;
import com.sicon.tba.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Clase que maneja las peticiones de menu
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class TbaMenuHandler {

    private final TbaMenuService tbaMenuService;
    private final ObjectValidator objectValidator;

    /**
     * Método que crea un menu
     *
     * @param request petición
     * @return menu creado
     */
    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<TbaMenuUsuarioResponseDto> tbaMenuDtoMono = request.bodyToMono(TbaMenuUsuarioResponseDto.class).doOnNext(objectValidator::validate);
        return tbaMenuDtoMono.flatMap(tbaMenuDto ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                .body(tbaMenuService.createMenu(tbaMenuDto), TbaMenu.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que actualiza un menu
     *
     * @param request petición
     * @return menu actualizado
     */
    public Mono<ServerResponse> update(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<TbaMenuUsuarioResponseDto> tbaMenuDtoMono = request.bodyToMono(TbaMenuUsuarioResponseDto.class).doOnNext(objectValidator::validate);
        return tbaMenuDtoMono.flatMap(tbaMenuDto ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaMenuService.updateMenu(id, tbaMenuDto), TbaMenu.class)
                )
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene un menu por id
     *
     * @param request petición
     * @return menu
     */
    public Mono<ServerResponse> getById(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<TbaMenu> tbaMenuMono = tbaMenuService.getMenuById(id);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaMenuMono, TbaMenu.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene todos los menus
     *
     * @param request petición
     * @return menus
     */
    public Mono<ServerResponse> getBySearchParam(ServerRequest request) {
        Integer menuFatherId = request.queryParam("menuFatherId").map(Integer::valueOf).orElse(null);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaMenuService.getBySearchParam(menuFatherId), TbaMenu.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getRequestBySearchParams(ServerRequest request) {
        Integer menuId = request.queryParam("menuId").map(Integer::valueOf).orElse(null);
        String menuName = request.queryParam("menuName").orElse(null);
        Integer menuLevel = request.queryParam("menuLevel").map(Integer::valueOf).orElse(null);
        Integer page = Integer.valueOf(request.queryParam("page").orElse("1"));
        Integer limit = Integer.valueOf(request.queryParam("limit").orElse("10"));
        String sortDirection = request.queryParam("sortDirection").orElse(null);
        String sortField = request.queryParam("sortField").orElse(null);

         if (request.queryParams().isEmpty()) {
            return getBySearchParam(request);
        }

        return ServerResponse.ok().body(tbaMenuService.getRequestBySearchParams(menuId, menuName, menuLevel, page, limit, sortDirection, sortField), TbaRol.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
