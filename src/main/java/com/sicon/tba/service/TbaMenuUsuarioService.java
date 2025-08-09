package com.sicon.tba.service;

import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.dto.TbaMenuUsuarioDto;
import com.sicon.tba.models.TbaMenu;
import com.sicon.tba.models.TbaMenuUsuario;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.repository.TbaMenuRepository;
import com.sicon.tba.repository.TbaMenuUsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


/**
 * Clase que contiene los metodos para el manejo de la tabla TBA_MENU_USUARIO
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class TbaMenuUsuarioService {

    private final TbaMenuUsuarioRepository tbaMenuUsuarioRepository;

    private final TbaMenuRepository tbaMenuRepository;

    /**
     * Método que permite crear un registro en la tabla TBA_MENU_USUARIO
     * @param userMenuDto Objeto que contiene los datos del registro a crear
     * @return Objeto Mono de TbaMenuUsuario
     */
    public Mono<TbaMenuUsuario> createUserMenu(TbaMenuUsuarioDto userMenuDto) {
        return tbaMenuUsuarioRepository.save(TbaMenuUsuario.builder()
                .estadoMenuUsuario(userMenuDto.getEstadoMenuUsuario())
                .idMenu(userMenuDto.getIdMenu())
                .idUsuario(userMenuDto.getIdUsuario())
                .build());
    }

    /**
     * Método que permite crear los menus para un nuevo usuario
     * @param userId Identificador del usuario
     * @return Objeto Mono de Boolean
     */
    public Flux<TbaMenuUsuario> createMenuForNewUser (Integer userId) {
        Flux<TbaMenu> tbaMenuFlux = tbaMenuRepository.findAll();
        List<TbaMenuUsuario> menuUsuarios = new ArrayList<>();
        return tbaMenuFlux.flatMap(tbaMenu -> {
                    TbaMenuUsuario tbaMenuUsuario = TbaMenuUsuario.builder()
                            .estadoMenuUsuario(Boolean.FALSE)
                            .idMenu(tbaMenu.getIdMenu())
                            .idUsuario(userId)
                            .build();
                    menuUsuarios.add(tbaMenuUsuario);
                    return Mono.just(tbaMenu);
                })
                .collectList()
                .flatMapMany(menuList -> tbaMenuUsuarioRepository.saveAll(menuUsuarios));
    }

    /**
     * Método que permite actualizar los menus de un usuario
     * @param tbaMenuUsuarioDtoList Lista de objetos que contiene los datos del registro a actualizar
     * @return Objeto Mono de Boolean
     */
    public Flux<TbaMenuUsuario> updateUserMenuByList (List<TbaMenuUsuario> tbaMenuUsuarioDtoList) {
        return tbaMenuUsuarioRepository.saveAll(tbaMenuUsuarioDtoList).onErrorMap(e -> {
                    log.error("Error al guardar los menus por usuario");
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que permite actualizar un registro en la tabla TBA_MENU_USUARIO
     * @param userMenuId Identificador del registro a actualizar
     * @param tbaMenuUsuario Objeto que contiene los datos del registro a actualizar
     * @return Objeto Mono de TbaMenuUsuario
     */
    public Mono<TbaMenuUsuario> updateUserMenu(Integer userMenuId, TbaMenuUsuarioDto tbaMenuUsuario) {
        return tbaMenuUsuarioRepository.save(
                TbaMenuUsuario.builder()
                        .idMenuUsuario(userMenuId)
                        .estadoMenuUsuario(tbaMenuUsuario.getEstadoMenuUsuario())
                        .idMenu(tbaMenuUsuario.getIdMenu())
                        .idUsuario(tbaMenuUsuario.getIdUsuario())
                        .build()
        );
    }

    /**
     * Método que consulta un registro en la tabla TBA_MENU_USUARIO por su identificador
     * @param userMenuId Identificador del registro a consultar
     * @return Objeto Mono de TbaMenuUsuario
     */
    public Mono<TbaMenuUsuario> getUserMenuById(Integer userMenuId) {
        return tbaMenuUsuarioRepository.findById(userMenuId);
    }

    /**
     * Método que consulta los registros de la tabla TBA_MENU_USUARIO por los parámetros enviados
     * @param menuId Identificador del menu
     * @param userId Identificador del usuario
     * @return Objeto Flux de TbaMenuUsuario
     */
    public Flux<TbaMenuUsuario> getUserMenusByParams(Integer menuId, Integer userId) {
        Boolean searchByMenuId = menuId != null && userId == null;
        Boolean searchByUserId = menuId == null && userId != null;
        if (searchByMenuId) {
            return tbaMenuUsuarioRepository.findByIdMenu(menuId);
        } else if (searchByUserId) {
            return tbaMenuUsuarioRepository.findByIdUsuario(userId);
        }else{
            return Flux.error(new CustomException(HttpStatus.BAD_REQUEST, ErrorMessage.ERROR_SEARCH_PARAMS ));
        }
    }
}
