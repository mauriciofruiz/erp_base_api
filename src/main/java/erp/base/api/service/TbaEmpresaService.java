package erp.base.api.service;

import erp.base.api.models.TbaEmpresa;
import erp.base.api.repository.TbaEmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TbaEmpresaService {

    private final TbaEmpresaRepository tbaEmpresaRepository;

    public Mono<TbaEmpresa> findById(Long id) {
        return tbaEmpresaRepository.findById(id);
    }

    public Mono<Void> save(TbaEmpresa tbaEmpresa) {
        return tbaEmpresaRepository.save(tbaEmpresa)
                .then();
    }

}
