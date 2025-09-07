package erp.base.api.repository;

import erp.base.api.models.AdqFacturaProveedor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface AdqFacturaProveedorRepository extends ReactiveCrudRepository<AdqFacturaProveedor, Integer> {

    Flux<AdqFacturaProveedor> findByIdProveedorAndIdEstadoFacturaProveedorNotIn(Integer idProveedor, List<Integer> idEstadoFacturaProveedorList);

}
