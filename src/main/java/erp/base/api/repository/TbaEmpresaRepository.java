package erp.base.api.repository;

import erp.base.api.models.TbaEmpresa;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaEmpresaRepository extends ReactiveCrudRepository<TbaEmpresa, Long> {

}
