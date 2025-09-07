package erp.base.api.repository;

import erp.base.api.models.Sesion;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SesionRepository extends ReactiveCrudRepository<Sesion, Integer> {

    Mono<Sesion> findByToken(String token);

    Mono<Sesion> findByIdUsr(Integer idUsr);

    Mono<Boolean> existsSesionByIdUsrAndEstado(Integer idUsr, Boolean estado);

    Mono<Sesion> findByIdUsrAndEstado(Integer idUsr, Boolean estado);
}
