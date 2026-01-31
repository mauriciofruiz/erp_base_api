package erp.base.api.repository

import erp.base.api.models.Departamento
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface DepartamentoRepository : ReactiveCrudRepository<Departamento, Int> {

    fun findByNombreDepartamentoContaining(nombreDepartamento: String): Flux<Departamento>
    fun findByEstadoDepartamento(estadoDepartamento: Boolean): Flux<Departamento>
    fun findByIdAreaGrupo(idAreaGrupo: Int): Flux<Departamento>
}
