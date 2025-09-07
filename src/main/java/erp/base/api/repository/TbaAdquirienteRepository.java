package erp.base.api.repository;

import erp.base.api.models.TbaAdquiriente;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TbaAdquirienteRepository extends ReactiveCrudRepository<TbaAdquiriente, Integer> {
    Flux<TbaAdquiriente> findAllByEstadoAdquiriente(Boolean estadoAdquiriente);
}
