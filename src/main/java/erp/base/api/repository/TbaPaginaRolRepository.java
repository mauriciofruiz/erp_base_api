package erp.base.api.repository;

import erp.base.api.models.TbaPaginaRol;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaPaginaRolRepository extends ReactiveCrudRepository<TbaPaginaRol, Integer> {
}
