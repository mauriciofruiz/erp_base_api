package erp.base.api.repository;

import erp.base.api.models.TbaTipoMovimientoBanco;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TbaTipoMovimientoBancoRepository extends ReactiveCrudRepository<TbaTipoMovimientoBanco, Integer> {
}
