package com.sicon.tba.service;

import com.sicon.tba.constants.Constants;
import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.dto.SesionDto;
import com.sicon.tba.models.Sesion;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.Sucursal;
import com.sicon.tba.models.TbaRol;
import com.sicon.tba.models.TbaRolUsuario;
import com.sicon.tba.repository.SesionRepository;
import com.sicon.tba.repository.custom.TbaSesionCustom;
import com.sicon.tba.utils.Paginacion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Clase que contiene los métodos para el manejo de las sesiones
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SesionService {

    private final SesionRepository sesionRepository;
    private final TbaSesionCustom tbaSesionCustom;

    /**
     * Método para crear una sesión
     *
     * @param sesionDto Objeto con los datos de la sesión
     * @return Objeto con los datos de la sesión creada
     */
    public Mono<Sesion> createSesion(Sesion sesionDto) {

        return sesionRepository.save(Sesion.builder()
                .idUsr(sesionDto.getIdUsr())
                .token(sesionDto.getToken())
                .fechaCreacion(sesionDto.getFechaCreacion())
                .fechaExpiracion(sesionDto.getFechaExpiracion())
                .estado(sesionDto.getEstado())
                .duracionMinutos(sesionDto.getDuracionMinutos())
                .build());
    }

    /**
     * Método para actualizar una sesión
     *
     * @param id        Id de la sesión a actualizar
     * @param sesionDto Objeto con los datos de la sesión
     * @return Objeto con los datos de la sesión actualizada
     */
    public Mono<Sesion> updateSesion(Integer id, Sesion sesionDto) {
        return sesionRepository.save(Sesion.builder()
                .idSesion(id)
                .idUsr(sesionDto.getIdUsr())
                .token(sesionDto.getToken())
                .fechaCreacion(sesionDto.getFechaCreacion())
                .fechaExpiracion(sesionDto.getFechaExpiracion())
                .estado(sesionDto.getEstado())
                .duracionMinutos(sesionDto.getDuracionMinutos())
                .build());

    }

    /**
     * Método para obtener una sesión por su token
     *
     * @param token Token de la sesión a obtener
     * @return Objeto con los datos de la sesión
     */
    public Mono<Sesion> getSessionByToken(String token) {
        return sesionRepository.findByToken(token)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.SESSION_NOT_FOUND)));
    }

    /**
     * Método para cerrar la sesion activa de un usuario
     *
     * @param id de la sesión a obtener
     * @return Objeto con los datos de la sesión
     */
    public Mono<Sesion> updateStatusSesion(Integer id) {
        return sesionRepository.findById(id).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.SESSION_NOT_FOUND + id)))
                .flatMap(sesion -> {
                    sesion.setEstado(false);
                    return sesionRepository.save(sesion);
                });
    }

    /**
     * Método para cerrar las sesiones activas de todos los usuarios
     *
     * @param sesionList de la sesión a obtener
     * @return Objeto con los datos de la sesión
     */
    public Flux<Sesion> updateAllSesion(List<SesionDto> sesionList) {
        return Flux.fromIterable(sesionList)
                .flatMap(sesion -> sesionRepository.findById(sesion.getIdSesion())
                        .flatMap(validSesion -> {
                            validSesion.setEstado(false);
                            return sesionRepository.save(validSesion);
                        })
                        .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Sesion con ID " + sesion.getIdSesion() + " no encontrada")))
                )
                .onErrorMap(e -> {
                    log.error("Error al actualizar las sesiones", e);
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<Paginacion<SesionDto>> getRequestBySearchParams(
            Integer userId,
            Integer page,
            Integer limit,
            String sortDirection,
            String sortfield
    ) {
        Flux<SesionDto> sesionFlux = tbaSesionCustom.getBySearchParams(userId, sortfield, sortDirection, limit, (page - 1) * limit);
        Mono<Long> total = tbaSesionCustom.getCountBySearchParams(userId);
        return total.flatMap(totalCount -> sesionFlux.collectList().map(sesionList -> new Paginacion<>(sesionList, page, limit, totalCount.intValue())));
    }

    public Flux<Sesion> getAllSessions() {
        return sesionRepository.findAll();
    }

    public Mono<HttpHeaders> getHeadersByUser(Integer idUsr) {
        return sesionRepository.findByIdUsrAndEstado(idUsr, true)
                .map(sesion -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setBearerAuth(sesion.getToken());
                    headers.set(Constants.USER_ID_HEADER, sesion.getIdUsr().toString());
                    return headers;
                });
    }

}
