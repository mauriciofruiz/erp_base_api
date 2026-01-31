package erp.base.api.repository

import erp.base.api.models.SustentoTributario
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SustentoTributarioRepository : ReactiveCrudRepository<SustentoTributario, String>
