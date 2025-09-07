package erp.base.api.repository;

import erp.base.api.models.TbaUnidadMedida;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaUnidadMedidaRepository extends ReactiveCrudRepository<TbaUnidadMedida, Integer> {
}
