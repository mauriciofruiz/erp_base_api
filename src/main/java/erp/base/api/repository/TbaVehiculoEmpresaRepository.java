package erp.base.api.repository;

import erp.base.api.models.TbaVehiculoEmpresa;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaVehiculoEmpresaRepository extends ReactiveCrudRepository<TbaVehiculoEmpresa, Integer> {
}
