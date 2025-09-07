package erp.base.api.repository;

import erp.base.api.models.TrbIdentificacionFiscal;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TrbIdentificacionFiscalRepository extends ReactiveCrudRepository<TrbIdentificacionFiscal, Integer> {
    Mono<TrbIdentificacionFiscal> findByCodIdentificacionFiscal(String codIdentificacionFiscal);

}
