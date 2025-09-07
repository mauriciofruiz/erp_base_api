package erp.base.api.repository;

import erp.base.api.models.TbaDetDeposito;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaDetDepositoRepository extends ReactiveCrudRepository<TbaDetDeposito, Integer> {
}
