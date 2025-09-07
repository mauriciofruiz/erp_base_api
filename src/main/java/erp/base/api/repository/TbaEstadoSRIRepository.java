package erp.base.api.repository;

import erp.base.api.models.TbaEstadoSRI;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaEstadoSRIRepository extends ReactiveCrudRepository<TbaEstadoSRI, Integer> {
}
