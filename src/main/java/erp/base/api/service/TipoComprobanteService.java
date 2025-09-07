package erp.base.api.service;

import erp.base.api.models.TipoComprobante;
import erp.base.api.repository.TipoComprobanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class TipoComprobanteService {


    private final TipoComprobanteRepository tipoComprobanteRepository;

    /**
     * Método para obtener todas los tipos de comprobantes
     *
     * @return Flux<TipoComprobante> objeto que contiene los datos del tipo de sustento tributario
     */
    public Flux<TipoComprobante> getAllTipoComprobante() {
        return tipoComprobanteRepository.findAll();
    }
}
