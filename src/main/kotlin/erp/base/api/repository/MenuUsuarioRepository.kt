package erp.base.api.repository

import erp.base.api.dto.MenuUsuarioResponseDto
import erp.base.api.models.MenuUsuario
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface MenuUsuarioRepository : ReactiveCrudRepository<MenuUsuario, Int> {

    @Query("SELECT m.ID_MENU, m.NOMBRE_MENU, m.PADRE_MENU, m.NIVEL_MENU, m.ICON_MENU, m.URL_MENU, m.COMPONENT_MENU, mu.ESTADO_MENU_USUARIO FROM MENU m JOIN MENUUSUARIO mu ON m.ID_MENU = mu.ID_MENU WHERE mu.ID_USUARIO = :userId and m.estado_menu ORDER BY m.orden_menu, mu.ID_MENU")
    fun findMenusByUserId(userId: Int): Flux<MenuUsuarioResponseDto>

    fun findByIdMenu(menuId: Int): Flux<MenuUsuario>
    fun findByIdUsuario(userId: Int): Flux<MenuUsuario>
}
