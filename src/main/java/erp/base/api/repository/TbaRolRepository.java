package erp.base.api.repository;

import erp.base.api.models.TbaRol;
import erp.base.api.repository.custom.TbaRolRepositoryCustom;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaRolRepository extends ReactiveCrudRepository<TbaRol, Integer>, TbaRolRepositoryCustom {
}
