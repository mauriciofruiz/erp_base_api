package com.sicon.tba.repository;

import com.sicon.tba.models.TbaRolUsuario;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TbaRolUsuarioRepository extends ReactiveCrudRepository<TbaRolUsuario, Integer> {
    Flux<TbaRolUsuario> findByIdUsuario(Integer idUsuario);
}
