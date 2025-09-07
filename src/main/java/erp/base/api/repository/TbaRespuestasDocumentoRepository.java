package erp.base.api.repository;

import erp.base.api.models.TbaRespuestasDocumento;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaRespuestasDocumentoRepository extends ReactiveCrudRepository<TbaRespuestasDocumento, Integer> {
}
