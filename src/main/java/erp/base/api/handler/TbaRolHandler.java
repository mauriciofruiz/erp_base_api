package erp.base.api.handler;

import erp.base.api.dto.RolResponseDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TbaRol;
import erp.base.api.service.TbaRolService;
import erp.base.api.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Log4j2
public class TbaRolHandler {

    private final TbaRolService service;
    private final ObjectValidator validator;

    public Mono<ServerResponse> createRole(ServerRequest request) {
        Mono<TbaRol> tbaRolMono = request.bodyToMono(TbaRol.class).doOnNext(validator::validate);
        return tbaRolMono.flatMap(rol ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(service.createRole(rol), TbaRol.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });

//        return request.bodyToMono(TbaRol.class).doOnNext(validator::validate)
//                .flatMap(service::createRole)
//                .flatMap(role -> ServerResponse.ok().body(Mono.just(role), TbaRol.class));
    }

    public Mono<ServerResponse> updateRole(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return request.bodyToMono(TbaRol.class)
                .flatMap(tbaRol -> service.updateRole(id, tbaRol))
                .flatMap(role -> ServerResponse.ok().body(Mono.just(role), TbaRol.class));
    }

    public Mono<ServerResponse> deleteRole(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return service.deleteRole(id)
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> getRoleById(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return service.getRoleById(id)
                .flatMap(role -> ServerResponse.ok().body(Mono.just(role), TbaRol.class));
    }

    public Mono<ServerResponse> getAllRoles(ServerRequest request) {
        return ServerResponse.ok().body(service.getAllRoles(), TbaRol.class);
    }

    public Mono<ServerResponse> getBySearchParams(ServerRequest request) {
        Integer page = Integer.valueOf(request.queryParam("page").orElse("1"));
        Integer limit = Integer.valueOf(request.queryParam("limit").orElse("10"));
        String sortDirection = request.queryParam("sortDirection").orElse(null);
        String sortField = request.queryParam("sortField").orElse(null);

        if (request.queryParams().isEmpty()) {
            return getAllRoles(request);
        }

        return ServerResponse.ok().body(service.getRequestBySearchParams(page, limit, sortDirection, sortField), TbaRol.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

}
