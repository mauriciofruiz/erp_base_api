package erp.base.api.repository.rrh;

import erp.base.api.models.rrh.RrhCargaFamiliar;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RrhCargaFamiliarRepository extends ReactiveCrudRepository<RrhCargaFamiliar, Integer> {

   Mono<Boolean>  existsByCedulaCargaFml(String cedula);
    Flux<RrhCargaFamiliar> findByIdEmpleado(Integer idEmpleado);
}
