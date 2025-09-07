package erp.base.api.repository;

import erp.base.api.models.TrbTipoPago;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrbTipoPagoRepository extends ReactiveCrudRepository<TrbTipoPago, Integer> {
}
