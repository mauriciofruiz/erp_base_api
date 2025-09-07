package erp.base.api.repository;

import erp.base.api.models.TbaBancoCatalogo;
import erp.base.api.repository.custom.TbaBancoCatalogoRespositoryCustom;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TbaBancoCatalogoRepository extends ReactiveCrudRepository<TbaBancoCatalogo, Integer>, TbaBancoCatalogoRespositoryCustom {

    Flux<TbaBancoCatalogo> findAllByEstadoBanco(Boolean estadoBanco);

}
