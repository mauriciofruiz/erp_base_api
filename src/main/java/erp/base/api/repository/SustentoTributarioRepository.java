package erp.base.api.repository;

import erp.base.api.models.SustentoTributario;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SustentoTributarioRepository extends ReactiveCrudRepository<SustentoTributario, Integer> {
}
