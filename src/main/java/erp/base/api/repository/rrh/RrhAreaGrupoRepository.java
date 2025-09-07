package erp.base.api.repository.rrh;

import erp.base.api.models.rrh.RrhAreaGrupo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RrhAreaGrupoRepository extends ReactiveCrudRepository<RrhAreaGrupo, Integer> {

    Flux<RrhAreaGrupo> findByNombreAreaGrupoContaining(String nombreAreaGrupo);
}
