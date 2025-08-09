package com.sicon.tba.service;

import com.sicon.tba.models.TbaTarjeta;
import com.sicon.tba.repository.*;
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