package erp.base.api.repository;

import erp.base.api.models.TbaImgUsr;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaImgUsrRepository extends ReactiveCrudRepository<TbaImgUsr, Integer> {
}
