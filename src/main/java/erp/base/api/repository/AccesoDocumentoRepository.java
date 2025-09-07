package erp.base.api.repository;

import erp.base.api.models.AccesoDocumento;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface AccesoDocumentoRepository extends ReactiveCrudRepository<AccesoDocumento, Integer> {
    Mono<AccesoDocumento> findByIdDocumentoAndIdTipoDocumentoAndNameUsuario(Integer documentId, Integer documentTypeId, String nameUsuario);

    Mono<AccesoDocumento> findByIdDocumentoAndIdTipoDocumento(Integer documentId, Integer documentTypeId);

    Flux<AccesoDocumento> findByNameUsuario(String nameUsuario);
}
