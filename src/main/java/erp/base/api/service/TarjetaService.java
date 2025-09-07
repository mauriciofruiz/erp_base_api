package erp.base.api.service;

import erp.base.api.models.TbaTarjeta;
import erp.base.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
@RequiredArgsConstructor
public class TarjetaService {

    private final TbaTarjetaRepository tarjetaRepository;

    public Flux<TbaTarjeta> getAllTarjetas() {
        return tarjetaRepository.findAllByEstadoTarjeta(Boolean.TRUE);
    }
}
