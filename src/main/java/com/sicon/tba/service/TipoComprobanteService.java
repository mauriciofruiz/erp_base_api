package com.sicon.tba.service;

import com.sicon.tba.models.TipoComprobante;
import com.sicon.tba.repository.TipoComprobanteRepository;
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
