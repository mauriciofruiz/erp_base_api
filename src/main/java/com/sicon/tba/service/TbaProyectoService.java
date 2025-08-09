package com.sicon.tba.service;

import com.sicon.tba.models.TbaProyecto;
import com.sicon.tba.repository.TbaProyectoRepository;
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
