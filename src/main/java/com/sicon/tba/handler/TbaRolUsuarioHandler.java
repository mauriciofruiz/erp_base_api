package com.sicon.tba.handler;

import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.TbaRolUsuario;
import com.sicon.tba.service.TbaRolUsuarioService;
import com.sicon.tba.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TbaRolUsuarioHandler {

    private final TbaRolUsuarioService tbaRolUsuarioService;

    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(TbaRolUsuario.class)
                .doOnNext(objectValidator::validate)
                .flatMap(tbaRolUsuarioService::create)
                .flatMap(tbaRolUsuario -> ServerResponse.ok().bodyValue(tbaRolUsuario))
                .onErrorResume(error -> Mono.error(new CustomException(HttpStatus.BAD_REQUEST, error.getMessage())));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        return request.bodyToMono(TbaRolUsuario.class)
                .doOnNext(objectValidator::validate)
                .flatMap(tbaRolUsuario -> tbaRolUsuarioService.update(Integer.parseInt(request.pathVariable("id")), tbaRolUsuario))
                .flatMap(tbaRolUsuario -> ServerResponse.ok().bodyValue(tbaRolUsuario))
                .onErrorResume(error -> Mono.error(new CustomException(HttpStatus.BAD_REQUEST, error.getMessage())));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return tbaRolUsuarioService.deleteUserRole(id)
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return tbaRolUsuarioService.findById(Integer.parseInt(request.pathVariable("id")))
                .flatMap(tbaRolUsuario -> ServerResponse.ok().bodyValue(tbaRolUsuario))
                .onErrorResume(error -> Mono.error(new CustomException(HttpStatus.BAD_REQUEST, error.getMessage())));
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok().body(tbaRolUsuarioService.findAll(), TbaRolUsuario.class);
    }

    public Mono<ServerResponse> findRolByUserId(ServerRequest request) {
        int userId = Integer.parseInt(request.pathVariable("id"));
        return ServerResponse.ok().body(tbaRolUsuarioService.findRolByUserId(userId), TbaRolUsuario.class)
                .onErrorResume(error -> Mono.error(new CustomException(HttpStatus.BAD_REQUEST, error.getMessage())));
    }
}
