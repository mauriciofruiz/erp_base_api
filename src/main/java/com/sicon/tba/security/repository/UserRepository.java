package com.sicon.tba.security.repository;

import com.sicon.tba.models.TbaUsuario;
import com.sicon.tba.security.entity.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<TbaUsuario, Integer> {

    Mono<User> findByNickUsrAndEstadoUsr(String nickUsr, Boolean estadoUsr);
}
