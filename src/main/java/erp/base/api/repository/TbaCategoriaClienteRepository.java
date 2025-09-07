package erp.base.api.repository;

import erp.base.api.models.TbaCategoriaCliente;
import erp.base.api.repository.custom.TbaCategoryClienteRepositoryCustom;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TbaCategoriaClienteRepository extends ReactiveCrudRepository<TbaCategoriaCliente, Integer>, TbaCategoryClienteRepositoryCustom {
    Flux<TbaCategoriaCliente> findByCategoriaEspecialCategoriaClienteAndActivoCategoriaCliente(Boolean categoriaEspecial, Boolean activo);
}
