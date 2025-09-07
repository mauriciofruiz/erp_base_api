package erp.base.api.repository;


import erp.base.api.models.TrbOrigenIngreso;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrbOrigenIngresoRepository extends ReactiveCrudRepository<TrbOrigenIngreso, Integer> {
}
