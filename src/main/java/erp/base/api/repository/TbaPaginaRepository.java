package erp.base.api.repository;

import erp.base.api.models.TbaPagina;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaPaginaRepository extends ReactiveCrudRepository<TbaPagina, Integer> {
}
