package com.sicon.tba.service;

import com.sicon.tba.models.TbaAdquiriente;
import com.sicon.tba.models.TbaTarjeta;
import com.sicon.tba.repository.TbaAdquirienteRepository;
import com.sicon.tba.repository.TbaTarjetaRepository;
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