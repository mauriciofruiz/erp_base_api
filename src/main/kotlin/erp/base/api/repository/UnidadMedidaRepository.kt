package erp.base.api.repository

import erp.base.api.models.UnidadMedida
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UnidadMedidaRepository : ReactiveCrudRepository<UnidadMedida, Int>
