package erp.base.api.repository;

import erp.base.api.models.Retencion;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RetencionRepository extends ReactiveCrudRepository<Retencion, Integer> {

    Flux<Retencion> findByEstadoRetencionAndEsProveedorAndIdTipoRetencion(Boolean estadoRetencion, Boolean esProveedor, Integer idTipoRetencion);

    Flux<Retencion> findByEstadoRetencionAndEsClienteAndIdTipoRetencion(Boolean estadoRetencion, Boolean esCliente, Integer idTipoRetencion);
}
