package erp.base.api.repository;

import erp.base.api.models.TbaDeposito;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaDepositoRepository extends ReactiveCrudRepository<TbaDeposito, Integer> {
}
