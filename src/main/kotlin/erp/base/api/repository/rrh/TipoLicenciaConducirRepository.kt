package erp.base.api.repository.rrh

import erp.base.api.models.rrh.TipoLicenciaConducir
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TipoLicenciaConducirRepository : ReactiveCrudRepository<TipoLicenciaConducir, Int>
