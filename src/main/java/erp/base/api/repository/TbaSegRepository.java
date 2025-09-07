package erp.base.api.repository;

import erp.base.api.models.TbaSeg;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TbaSegRepository extends ReactiveCrudRepository<TbaSeg, Integer> {
}
