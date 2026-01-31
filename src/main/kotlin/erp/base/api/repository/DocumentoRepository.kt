package erp.base.api.repository

import erp.base.api.models.Documento
import erp.base.api.repository.custom.DocumentoRepositoryCustom
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentoRepository : ReactiveCrudRepository<Documento, Int>, DocumentoRepositoryCustom
