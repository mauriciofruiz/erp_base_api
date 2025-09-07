package erp.base.api.repository;

import erp.base.api.models.TbaCondicionPago;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaCondicionPagoRepository extends ReactiveCrudRepository<TbaCondicionPago, Integer> {
}
