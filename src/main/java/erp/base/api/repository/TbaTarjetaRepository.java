package erp.base.api.repository;

import erp.base.api.models.TbaTarjeta;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TbaTarjetaRepository extends ReactiveCrudRepository<TbaTarjeta, Integer> {
    Flux<TbaTarjeta> findAllByEstadoTarjeta(Boolean estadoTarjeta);
}
