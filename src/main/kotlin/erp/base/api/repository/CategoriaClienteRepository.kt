package erp.base.api.repository

import erp.base.api.models.CategoriaCliente
import erp.base.api.repository.custom.CategoryClienteRepositoryCustom
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface CategoriaClienteRepository : ReactiveCrudRepository<CategoriaCliente, Int>, CategoryClienteRepositoryCustom {

    fun findByCategoriaEspecialCategoriaClienteAndActivoCategoriaCliente(
        categoriaEspecial: Boolean,
        activo: Boolean
    ): Flux<CategoriaCliente>
}
