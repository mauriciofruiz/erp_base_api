package erp.base.api.repository;

import erp.base.api.models.TbaGenero;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaGeneroRepository extends ReactiveCrudRepository<TbaGenero, Integer> {
}
