package erp.base.api.repository.rrh

import erp.base.api.models.rrh.AreaGrupo
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface AreaGrupoRepository : ReactiveCrudRepository<AreaGrupo, Int> {

    fun findByNombreAreaGrupoContaining(nombreAreaGrupo: String): Flux<AreaGrupo>
}
