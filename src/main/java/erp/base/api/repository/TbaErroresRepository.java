package erp.base.api.repository;

import erp.base.api.models.TbaErrores;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaErroresRepository extends ReactiveCrudRepository<TbaErrores, Integer> {
}
