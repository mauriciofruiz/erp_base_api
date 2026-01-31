package erp.base.api.repository

import erp.base.api.models.DocumentosElectronicos
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentosElectronicosRepository : ReactiveCrudRepository<DocumentosElectronicos, Int>
