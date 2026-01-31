package erp.base.api.repository

import erp.base.api.models.VehiculoEmpresa
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface VehiculoEmpresaRepository : ReactiveCrudRepository<VehiculoEmpresa, Int>
