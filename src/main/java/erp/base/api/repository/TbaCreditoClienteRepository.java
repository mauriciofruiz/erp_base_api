package erp.base.api.repository;

import erp.base.api.models.TbaCreditoCliente;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TbaCreditoClienteRepository extends ReactiveCrudRepository<TbaCreditoCliente, Integer> {

    Mono<TbaCreditoCliente> findByIdCliente(Integer clientId);
}
