package erp.base.api.service;

import erp.base.api.models.TbaAdquiriente;
import erp.base.api.models.TbaTarjeta;
import erp.base.api.repository.TbaAdquirienteRepository;
import erp.base.api.repository.TbaTarjetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
@RequiredArgsConstructor
public class AdquirienteService {

    private final TbaAdquirienteRepository adquirienteRepository;

    public Flux<TbaAdquiriente> getAllByActiveEstado() {
        return adquirienteRepository.findAllByEstadoAdquiriente(Boolean.TRUE);
    }
}
