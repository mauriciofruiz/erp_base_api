package erp.base.api.repository;

import erp.base.api.models.TipoComprobante;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TipoComprobanteRepository extends ReactiveCrudRepository<TipoComprobante, Integer> {
}
