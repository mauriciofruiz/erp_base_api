package erp.base.api.repository.rrh;


import erp.base.api.models.rrh.RrhContratoLaboral;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RrhContratoLaboralRepository extends ReactiveCrudRepository<RrhContratoLaboral, Integer> {

    Flux<RrhContratoLaboral> findByIdEmpleado(Integer idEmpleado);
}
