package erp.base.api.repository;

import erp.base.api.models.TbaBanco;
import erp.base.api.repository.custom.TbaBancoRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TbaBancoRepository extends ReactiveCrudRepository<TbaBanco, Integer>, TbaBancoRepositoryCustom {

    Flux<TbaBanco> findAllBy(Pageable pageable);
    Flux<TbaBanco> findByNombreBancoContaining(String nombreBanco);
    Flux<TbaBanco> findByNumeroCuentaBancoContaining(String numeroCuenta);
    Mono<Long> countByNombreBanco(String nameBank);
    Mono<Long> countByNumeroCuentaBanco(String countByNumeroCuenta);
    Flux<TbaBanco> findByEstadoBanco(Boolean estadoBanco);
    Flux<TbaBanco> findByEstadoBancoAndEsCajaChicaBanco(Boolean estadoBanco, Boolean esCajaChicaBanco);
    @Query( "SELECT MAX(ultimonumcheque_banco) FROM tba_banco where id_banco = :idBanco and estado_banco = :estadoBanco")
    Mono<Integer> getLastNumberCheckBank(Integer idBanco, Boolean estadoBanco);
    Flux<TbaBanco> findByIdSucursalAndEstadoBanco(Integer idSucursal, Boolean estadoBanco);
}
