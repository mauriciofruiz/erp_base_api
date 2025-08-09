package com.sicon.tba.repository;

import com.sicon.tba.dto.RolResponseDto;
import com.sicon.tba.models.Rol;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface RolRepository extends ReactiveCrudRepository<Rol, Integer> {

    @Query("select tba_rol.id_rol, tba_rol.nombre_rol, tba_rol.descripcion_rol" +
            " from tba_rol" +
            " join tba_rolusuario on tba_rol.id_rol = tba_rolusuario.id_rol" +
            " where tba_rolusuario.id_usr = :userId" +
            "  and tba_rolusuario.estado_rolusuario" +
            " order by tba_rol.id_rol;")
    Flux<RolResponseDto> getActiveRolesByUser(Integer userId);
}
