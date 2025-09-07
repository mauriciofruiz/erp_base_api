package erp.base.api.handler;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TbaUsuarioRolesDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TbaUsuario;
import erp.base.api.service.TbaUsuarioService;
import erp.base.api.utils.Paginacion;
import erp.base.api.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.security.NoSuchAlgorithmException;

/**
 * Clase que maneja las peticiones de usuario
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class TbaUsuarioHandler {

    private final TbaUsuarioService tbaUsuarioService;

    private final ObjectValidator objectValidator;


    /**
     * Método que actualiza un usuario
     * @param request petición
     * @return usuario actualizado
     */
    public Mono<ServerResponse> updatePassword (ServerRequest request){
            Integer id = Integer.valueOf(request.pathVariable("id"));
            Mono<TbaUsuario> tbaUsuarioDtoMono = request.bodyToMono(TbaUsuario.class).doOnNext(objectValidator::validate).log().log();
            return tbaUsuarioDtoMono.flatMap(tbaUsuarioDto ->
                    ServerResponse.ok().body(tbaUsuarioService.updateUserPassword(id, tbaUsuarioDto), TbaUsuario.class))
                    .onErrorMap(e -> {
                        log.error(e.getMessage());
                        return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                    });

    }

    public Mono<ServerResponse> updateuser (ServerRequest request){
            Integer id = Integer.valueOf(request.pathVariable("id"));
            Mono<TbaUsuarioRolesDto> tbaUsuarioDtoMono = request.bodyToMono(TbaUsuarioRolesDto.class).doOnNext(objectValidator::validate).log().log();
            return tbaUsuarioDtoMono.flatMap(tbaUsuarioDto ->
                    ServerResponse.ok().body(tbaUsuarioService.updateUser(id, tbaUsuarioDto), TbaUsuario.class))
                    .onErrorMap(e -> {
                        log.error(e.getMessage());
                        return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                    });

    }

    /**
     * Método que obtiene un usuario por id
     * @param request petición
     * @return usuario
     */
    public Mono<ServerResponse> getById (ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<TbaUsuario> tbaUsuarioMono = tbaUsuarioService.getUserById(id);
        return ServerResponse.ok().body(tbaUsuarioMono, TbaUsuario.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene todos los usuarios
     * @param request petición
     * @return lista de usuarios
     */
    public Mono<ServerResponse> getUsersBySearchParams(ServerRequest request){
        // Recuperar los parámetros de solicitud
        String nick = request.queryParam("nick").orElse(null);
        Boolean status = request.queryParam("status").map(Boolean::valueOf).orElse(null);
        String identification = request.queryParam("identification").orElse(null);
        String name = request.queryParam("name").orElse(null);
        String pageParam = request.queryParam("page").orElse("1");
        String limitParam = request.queryParam("limit").orElse("10");

        // Convertir los parámetros de cadena a valores numéricos
        Integer page = Integer.parseInt(pageParam);
        Integer limit = Integer.parseInt(limitParam);

        Mono<Paginacion<TbaUsuario>> usuarioFlux = tbaUsuarioService.getUsersBySearchParams(nick,status,identification,name,page,limit);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(usuarioFlux, TbaUsuario.class).onErrorMap(e -> {
            log.error(e.getMessage());
            return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        });
    }

    public Mono<ServerResponse> forgotPassword(ServerRequest  request) {
        String nickName = request.queryParam("param").orElse(null);
        if(nickName !=  null){
            try {
                return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaUsuarioService.forgotPassword(nickName), TbaUsuario.class).onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        } else {
            return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, ErrorMessage.NICK_NAME_NOT_OBTAINED));
        }
    }

    public Mono<ServerResponse> getUsersRequestPurchase(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaUsuarioService.getUsersRequestPurchase(), TbaUsuario.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getUsersPurchaseOrder(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaUsuarioService.getUsersPurchaseOrder(), TbaUsuario.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getSalesUsers(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaUsuarioService.getSalesUser(), TbaUsuario.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
