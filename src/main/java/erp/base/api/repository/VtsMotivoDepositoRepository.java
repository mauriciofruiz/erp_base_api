package erp.base.api.repository;

import erp.base.api.models.VtsMotivoDeposito;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VtsMotivoDepositoRepository extends ReactiveCrudRepository<VtsMotivoDeposito, Integer> {
}
