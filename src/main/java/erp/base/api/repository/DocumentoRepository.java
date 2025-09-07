package erp.base.api.repository;

import erp.base.api.models.Documento;
import erp.base.api.repository.custom.DocumentoRepositoryCustom;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DocumentoRepository extends ReactiveCrudRepository<Documento, Integer>, DocumentoRepositoryCustom {
}
