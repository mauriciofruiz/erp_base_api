package erp.base.api.repository

import erp.base.api.models.Empresa
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmpresaRepository : ReactiveCrudRepository<Empresa, Long>
