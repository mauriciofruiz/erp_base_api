package erp.base.api.repository;

import erp.base.api.models.Caja;
import erp.base.api.models.TbaPersona;
import erp.base.api.repository.custom.TbaCajaRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TbaCajaRepository extends ReactiveCrudRepository<Caja, Integer>, TbaCajaRepositoryCustom {

    Flux<Caja> findByIdSucursal(Integer idSucursal);
}
