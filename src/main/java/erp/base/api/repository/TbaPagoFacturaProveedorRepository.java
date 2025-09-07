package erp.base.api.repository;

import erp.base.api.models.TbaPagoFacturaProveedor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaPagoFacturaProveedorRepository extends ReactiveCrudRepository<TbaPagoFacturaProveedor, Integer> {
}
