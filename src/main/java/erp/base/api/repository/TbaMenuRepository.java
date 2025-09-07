package erp.base.api.repository;

import erp.base.api.models.TbaMenu;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TbaMenuRepository extends ReactiveCrudRepository<TbaMenu, Integer> {
    Flux<TbaMenu> findByPadreMenu(Integer padreMenu);
}
