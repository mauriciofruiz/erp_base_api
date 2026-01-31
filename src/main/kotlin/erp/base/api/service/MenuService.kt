package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.MenuDto
import erp.base.api.dto.MenuUsuarioResponseDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Menu
import erp.base.api.repository.MenuRepository
import erp.base.api.repository.custom.MenuCustom
import erp.base.api.utils.Paginacion
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class MenuService(
    private val tbaMenuRepository: MenuRepository,
    private val tbaMenuCustom: MenuCustom
) {

    fun createMenu(userMenuDto: MenuUsuarioResponseDto): Mono<Menu> =
        tbaMenuRepository.save(
            Menu(
                idMenu = null,
                iconMenu = userMenuDto.iconMenu,
                nivelMenu = userMenuDto.nivelMenu,
                nombreMenu = userMenuDto.nombreMenu,
                urlMenu = userMenuDto.urlMenu,
                padreMenu = userMenuDto.padreMenu,
                componenteMenu = userMenuDto.componentMenu,
                ordenMenu = null,
                estadoMenu = null
            )
        )

    fun updateMenu(menuId: Int, userMenuResponseDto: MenuUsuarioResponseDto): Mono<Menu> {
        val menuExists = tbaMenuRepository.existsById(menuId)
        return menuExists.flatMap { exists ->
            if (exists) {
                tbaMenuRepository.save(
                    Menu(
                        idMenu = menuId,
                        iconMenu = userMenuResponseDto.iconMenu,
                        nivelMenu = userMenuResponseDto.nivelMenu,
                        nombreMenu = userMenuResponseDto.nombreMenu,
                        urlMenu = userMenuResponseDto.urlMenu,
                        padreMenu = userMenuResponseDto.padreMenu,
                        componenteMenu = userMenuResponseDto.componentMenu,
                        ordenMenu = null,
                        estadoMenu = null
                    )
                )
            } else {
                Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.MENU_NOT_FOUND + menuId))
            }
        }
    }

    fun getMenuById(menuId: Int): Mono<Menu> =
        tbaMenuRepository.findById(menuId)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.MENU_NOT_FOUND + menuId)))

    fun getBySearchParam(menuFatherId: Int?): Flux<Menu> =
        if (menuFatherId != null) tbaMenuRepository.findByPadreMenu(menuFatherId)
        else tbaMenuRepository.findAll()

    fun getRequestBySearchParams(
        menuId: Int?,
        menuName: String?,
        menuLevel: Int?,
        page: Int,
        limit: Int,
        sortDirection: String?,
        sortfield: String?
    ): Mono<Paginacion<MenuDto>> {
        val rolDtoFlux = tbaMenuCustom.getBySearchParams(menuId, menuName, menuLevel, sortfield, sortDirection, limit, (page - 1) * limit)
        val total = tbaMenuCustom.getCountBySearchParams(menuId, menuName, menuLevel)
        return total.flatMap { totalCount ->
            rolDtoFlux.collectList().map { rolDtoList -> Paginacion(rolDtoList, page, limit, totalCount.toInt()) }
        }
    }
}
