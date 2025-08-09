package com.sicon.tba.service;

import com.sicon.tba.models.TbaEmpresa;
import com.sicon.tba.repository.TbaEmpresaRepository;
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
