package erp.base.api.repository.rrh;

import erp.base.api.models.rrh.RrhDiscapacidad;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RrhDiscapacidadRepository extends ReactiveCrudRepository<RrhDiscapacidad, Integer> {
}
