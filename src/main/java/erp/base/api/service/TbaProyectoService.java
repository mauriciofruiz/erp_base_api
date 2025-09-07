package erp.base.api.service;

import erp.base.api.models.TbaProyecto;
import erp.base.api.repository.TbaProyectoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class TbaProyectoService {

    private final TbaProyectoRepository tbaProyectoRepository;

    public Flux<TbaProyecto> findAllProjectsActive() {
        return tbaProyectoRepository.findByEstadoProyecto(true);
    }
}
