package erp.base.api.repository;

import erp.base.api.models.TbaCargo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaCargoRepository extends ReactiveCrudRepository<TbaCargo, Integer> {
}
