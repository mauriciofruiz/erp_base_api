package erp.base.api.repository;

import erp.base.api.models.VtsFactura;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VtsFacturaRepository extends ReactiveCrudRepository<VtsFactura, Integer> {
}
