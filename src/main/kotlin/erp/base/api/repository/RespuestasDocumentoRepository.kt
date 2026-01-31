package erp.base.api.repository

import erp.base.api.models.RespuestasDocumento
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RespuestasDocumentoRepository : ReactiveCrudRepository<RespuestasDocumento, Int>
