package erp.base.api.repository;

import erp.base.api.models.TbaBloqueo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaBloqueoRepository extends ReactiveCrudRepository<TbaBloqueo, Integer> {
}
