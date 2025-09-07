package erp.base.api.repository;

import erp.base.api.dto.TbaUsuarioDto;
import erp.base.api.models.TbaUsuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TbaUsuarioRepository extends ReactiveCrudRepository<TbaUsuario, Integer> {


    Mono<TbaUsuario> findByNickUsrIgnoreCase(String nickUsr);
    Mono<Boolean> existsByNickUsrIgnoreCase(String nickUsr);
    Mono<Boolean> existsByIdPersona(Integer idPersona);
    Mono<TbaUsuario> findByIdPersona(Integer idPersona);

    Flux<TbaUsuario> findAllBy(Pageable pageable);

    Mono<Long> countByNickUsrContainingAndEstadoUsr(String nickUsr, Boolean estadoUsr);
    Flux<TbaUsuario> findByNickUsrContainingAndEstadoUsr(String nickUsr, Boolean estadoUsr, Pageable pageable);

    Mono<Long> countByEstadoUsr(Boolean estadoUsr);

    Flux<TbaUsuario> findByEstadoUsr(Boolean estadoUsr, Pageable pageable);

    @Query(" SELECT DISTINCT TU.*" +
            " FROM TBA_USUARIO TU" +
            " RIGHT JOIN ADQ_SOLICITUDCOMPRA ADQ on TU.ID_USR = ADQ.ID_USR" +
            " WHERE TU.ESTADO_USR =:estado")
    Flux<TbaUsuario> findUsersRequestPurchase(Boolean estado);

    @Query(" SELECT DISTINCT TU.*" +
            " FROM TBA_USUARIO TU" +
            " RIGHT JOIN ADQ_ORDENCOMPRA AO on TU.ID_USR = AO.ID_USR" +
            " WHERE TU.ESTADO_USR =:estado")
    Flux<TbaUsuario> findUsersPurchaseOrder(Boolean estado);
}
