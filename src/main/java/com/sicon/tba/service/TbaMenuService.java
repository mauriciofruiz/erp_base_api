package com.sicon.tba.service;

import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.dto.TbaMenuDto;
import com.sicon.tba.dto.TbaMenuUsuarioResponseDto;
import com.sicon.tba.models.TbaMenu;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.TbaRol;
import com.sicon.tba.repository.TbaMenuRepository;
import com.sicon.tba.repository.custom.TbaMenuCustom;
import com.sicon.tba.utils.Paginacion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase que contiene los métodos para el manejo de los menus
 */
@Service
@RequiredArgsConstructor
public class TbaMenuService {

    private final TbaMenuRepository tbaMenuRepository;
    private final TbaMenuCustom tbaMenuCustom;

    /**
     * Método para crear un nuevo menu
     *
     * @param userMenuDto Objeto con los datos del menu
     * @return Objeto con los datos del menu creado
     */
    public Mono<TbaMenu> createMenu(TbaMenuUsuarioResponseDto userMenuDto) {
        return tbaMenuRepository.save(
                TbaMenu.builder()
                        .iconMenu(userMenuDto.getIconMenu())
                        .nivelMenu(userMenuDto.getNivelMenu())
                        .nombreMenu(userMenuDto.getNombreMenu())
                        .urlMenu(userMenuDto.getUrlMenu())
                        .padreMenu(userMenuDto.getPadreMenu())
                        .componenteMenu(userMenuDto.getComponentMenu())
                        .build()
        );
    }

    /**
     * Método para actualizar un menu
     *
     * @param menuId              Identificador del menu
     * @param userMenuResponseDto Objeto con los datos del menu
     * @return Objeto con los datos del menu actualizado
     */
    public Mono<TbaMenu> updateMenu(Integer menuId, TbaMenuUsuarioResponseDto userMenuResponseDto) {
        Mono<Boolean> menuExists = tbaMenuRepository.existsById(menuId);
        return menuExists.flatMap(exists -> exists ?
                tbaMenuRepository.save(
                        TbaMenu.builder()
                                .idMenu(menuId)
                                .iconMenu(userMenuResponseDto.getIconMenu())
                                .nivelMenu(userMenuResponseDto.getNivelMenu())
                                .nombreMenu(userMenuResponseDto.getNombreMenu())
                                .urlMenu(userMenuResponseDto.getUrlMenu())
                                .padreMenu(userMenuResponseDto.getPadreMenu())
                                .componenteMenu(userMenuResponseDto.getComponentMenu())
                                .build()
                ) : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.MENU_NOT_FOUND + menuId))
        );
    }

    /**
     * Método para obtener el menu por Id
     *
     * @param menuId Identificador del menu
     * @return Objeto con los datos del menu
     */
    public Mono<TbaMenu> getMenuById(Integer menuId) {
        return tbaMenuRepository.findById(menuId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.MENU_NOT_FOUND + menuId)));
    }

    /**
     * Método para obtener todos los menus
     *
     * @return Lista de menus
     */
    public Flux<TbaMenu> getBySearchParam(Integer menuFatherId) {
        if (menuFatherId != null)
            return tbaMenuRepository.findByPadreMenu(menuFatherId);
        return tbaMenuRepository.findAll();
    }


    public Mono<Paginacion<TbaMenuDto>> getRequestBySearchParams(
            Integer menuId,
            String menuName,
            Integer menuLevel,
            Integer page,
            Integer limit,
            String sortDirection,
            String sortfield
    ) {
        Flux<TbaMenuDto> rolDtoFlux = tbaMenuCustom.getBySearchParams(menuId, menuName, menuLevel, sortfield, sortDirection, limit, (page - 1) * limit);
        Mono<Long> total = tbaMenuCustom.getCountBySearchParams(menuId, menuName, menuLevel);
        return total.flatMap(totalCount -> rolDtoFlux.collectList().map(rolDtoList -> new Paginacion<>(rolDtoList, page, limit, totalCount.intValue())));
    }

}
