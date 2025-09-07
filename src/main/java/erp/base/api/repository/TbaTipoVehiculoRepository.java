package erp.base.api.repository;

import erp.base.api.models.TbaTipoVehiculo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaTipoVehiculoRepository extends ReactiveCrudRepository<TbaTipoVehiculo, Integer>{
}
