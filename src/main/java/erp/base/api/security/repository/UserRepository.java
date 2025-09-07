package erp.base.api.security.repository;

import erp.base.api.models.TbaUsuario;
import erp.base.api.security.entity.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<TbaUsuario, Integer> {

    Mono<User> findByNickUsrAndEstadoUsr(String nickUsr, Boolean estadoUsr);
}
