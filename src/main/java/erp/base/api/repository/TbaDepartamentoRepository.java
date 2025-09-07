package erp.base.api.repository;

import erp.base.api.models.TbaDepartamento;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TbaDepartamentoRepository extends ReactiveCrudRepository<TbaDepartamento, Integer> {

    Flux<TbaDepartamento> findByNombreDepartamentoContaining(String nombreDepartamento);

    Flux<TbaDepartamento> findByEstadoDepartamento(Boolean estadoDepartamento);

    Flux<TbaDepartamento> findByIdAreaGrupo(Integer idAreaGrupo);
}
