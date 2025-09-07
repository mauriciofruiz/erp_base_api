package erp.base.api.repository;

import erp.base.api.models.TbaRegion;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TbaRegionRepository extends ReactiveCrudRepository<TbaRegion, Integer> {

    Flux<TbaRegion> findByNombreRegionContaining(String nombreRegion);
}
