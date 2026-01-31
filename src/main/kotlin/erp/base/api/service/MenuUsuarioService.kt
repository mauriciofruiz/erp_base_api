package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.MenuUsuarioDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Menu
import erp.base.api.models.MenuUsuario
import erp.base.api.repository.MenuRepository
import erp.base.api.repository.MenuUsuarioRepository
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class MenuUsuarioService(
    private val tbaMenuUsuarioRepository: MenuUsuarioRepository,
    private val tbaMenuRepository: MenuRepository
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun createUserMenu(userMenuDto: MenuUsuarioDto): Mono<MenuUsuario> =
        tbaMenuUsuarioRepository.save(
            MenuUsuario(
                idMenuUsuario = null,
                estadoMenuUsuario = userMenuDto.estadoMenuUsuario,
                idMenu = userMenuDto.idMenu,
                idUsuario = userMenuDto.idUsuario
            )
        )

    fun createMenuForNewUser(userId: Int): Flux<MenuUsuario> {
        val tbaMenuFlux = tbaMenuRepository.findAll()
        val menuUsuarios = mutableListOf<MenuUsuario>()
        return tbaMenuFlux.flatMap { tbaMenu ->
            val tbaMenuUsuario = MenuUsuario(
                idMenuUsuario = null,
                estadoMenuUsuario = false,
                idMenu = tbaMenu.idMenu,
                idUsuario = userId
            )
            menuUsuarios.add(tbaMenuUsuario)
            Mono.just(tbaMenu)
        }.collectList().flatMapMany { tbaMenuUsuarioRepository.saveAll(menuUsuarios) }
    }

    fun updateUserMenuByList(tbaMenuUsuarioDtoList: List<MenuUsuario>): Flux<MenuUsuario> =
        tbaMenuUsuarioRepository.saveAll(tbaMenuUsuarioDtoList).onErrorMap { e ->
            log.error("Error al guardar los menus por usuario")
            CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
        }

    fun updateUserMenu(userMenuId: Int, tbaMenuUsuario: MenuUsuarioDto): Mono<MenuUsuario> =
        tbaMenuUsuarioRepository.save(
            MenuUsuario(
                idMenuUsuario = userMenuId,
                estadoMenuUsuario = tbaMenuUsuario.estadoMenuUsuario,
                idMenu = tbaMenuUsuario.idMenu,
                idUsuario = tbaMenuUsuario.idUsuario
            )
        )

    fun getUserMenuById(userMenuId: Int): Mono<MenuUsuario> =
        tbaMenuUsuarioRepository.findById(userMenuId)

    fun getUserMenusByParams(menuId: Int?, userId: Int?): Flux<MenuUsuario> {
        val searchByMenuId = menuId != null && userId == null
        val searchByUserId = menuId == null && userId != null
        return when {
            searchByMenuId -> tbaMenuUsuarioRepository.findByIdMenu(menuId!!)
            searchByUserId -> tbaMenuUsuarioRepository.findByIdUsuario(userId!!)
            else -> Flux.error(CustomException(HttpStatus.BAD_REQUEST, ErrorMessage.ERROR_SEARCH_PARAMS))
        }
    }
}
