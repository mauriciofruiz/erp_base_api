package erp.base.api.repository.cont;

import erp.base.api.models.ConAsiento;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConAsientoRepository extends ReactiveCrudRepository<ConAsiento, Integer> {

}
