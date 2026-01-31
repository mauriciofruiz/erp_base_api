package erp.base.api.repository;

import erp.base.api.dto.TbaMenuDto;
import erp.base.api.dto.TbaMenuUsuarioResponseDto;
import erp.base.api.models.TbaMenuUsuario;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface TbaMenuUsuarioRepository extends ReactiveCrudRepository<TbaMenuUsuario, Integer> {

    @Query("SELECT m.ID_MENU, m.NOMBRE_MENU, " +
            "m.PADRE_MENU, m.NIVEL_MENU, " +
            "m.ICON_MENU, m.URL_MENU, m.COMPONENT_MENU, " +
            "mu.ESTADO_MENU_USUARIO " +
            "FROM MENU m " +
            "JOIN MENUUSUARIO mu ON m.ID_MENU = mu.ID_MENU " +
            "WHERE mu.ID_USUARIO = :userId" +
            " and m.estado_menu" +
            " ORDER BY m.orden_menu, mu.ID_MENU")
    Flux<TbaMenuUsuarioResponseDto> findMenusByUserId(Integer userId);

    Flux<TbaMenuUsuario> findByIdMenu(Integer menuId);

    Flux<TbaMenuUsuario> findByIdUsuario(Integer userId);
}
