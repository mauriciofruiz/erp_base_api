package erp.base.api.repository;

import erp.base.api.models.TbaCupon;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaCuponRepository extends ReactiveCrudRepository<TbaCupon, Integer> {
}
