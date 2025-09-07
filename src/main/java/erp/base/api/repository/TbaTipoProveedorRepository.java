package erp.base.api.repository;

import erp.base.api.models.TbaTipoProveedor;
import erp.base.api.models.TrbTipoContribuyente;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TbaTipoProveedorRepository extends ReactiveCrudRepository<TbaTipoProveedor, Integer> {

}
