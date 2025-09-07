package erp.base.api.repository;

import erp.base.api.models.TbaParametro;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaParametroRepository extends ReactiveCrudRepository<TbaParametro, Integer> {
}
