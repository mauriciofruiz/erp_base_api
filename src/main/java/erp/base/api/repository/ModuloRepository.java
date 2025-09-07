package erp.base.api.repository;

import erp.base.api.models.Modulo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuloRepository extends ReactiveCrudRepository<Modulo, Integer> {
}
