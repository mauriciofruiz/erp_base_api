package erp.base.api.repository

import erp.base.api.dto.RolResponseDto
import erp.base.api.models.Rol
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface RolRepository : ReactiveCrudRepository<Rol, Int> {

    @Query("""SELECT r.id_rol, r.nombre_rol, r.descripcion_rol
            FROM rol r
            JOIN rolusuario ru ON r.id_rol = ru.id_rol
            WHERE ru.id_usr = :userId AND ru.estado_rolusuario = true
            ORDER BY r.id_rol;""")
    fun getActiveRolesByUser(userId: Int): Flux<RolResponseDto>
}
