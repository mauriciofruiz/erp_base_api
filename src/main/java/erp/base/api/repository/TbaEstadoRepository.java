package erp.base.api.repository;

import erp.base.api.models.TbaEstado;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaEstadoRepository extends ReactiveCrudRepository<TbaEstado, Integer>  {
}
