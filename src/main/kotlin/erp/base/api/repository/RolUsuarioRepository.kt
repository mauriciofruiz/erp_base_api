package erp.base.api.repository

import erp.base.api.models.TbaRolUsuario
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface RolUsuarioRepository : ReactiveCrudRepository<TbaRolUsuario, Int> {

    fun findByIdUsuario(idUsuario: Int): Flux<TbaRolUsuario>
}
