package erp.base.api.repository

import erp.base.api.dto.RolResponseDto
import erp.base.api.models.Rol
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface RolRepository : ReactiveCrudRepository<Rol, Int> {

    @Query("select tba_rol.id_rol, tba_rol.nombre_rol, tba_rol.descripcion_rol from tba_rol join tba_rolusuario on tba_rol.id_rol = tba_rolusuario.id_rol where tba_rolusuario.id_usr = :userId and tba_rolusuario.estado_rolusuario order by tba_rol.id_rol;")
    fun getActiveRolesByUser(userId: Int): Flux<RolResponseDto>
}
