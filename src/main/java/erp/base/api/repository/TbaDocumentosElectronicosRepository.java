package erp.base.api.repository;

import erp.base.api.models.TbaDocumentosElectronicos;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaDocumentosElectronicosRepository extends ReactiveCrudRepository<TbaDocumentosElectronicos, Integer> {
}
