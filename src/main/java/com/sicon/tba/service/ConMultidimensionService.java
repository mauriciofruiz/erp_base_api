package com.sicon.tba.service;

import com.sicon.tba.enums.Multidimensional;
import com.sicon.tba.models.ConMultidimension;
import com.sicon.tba.repository.cont.ConMultidimensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ConMultidimensionService {

    private final ConMultidimensionRepository conMultidimensionRepository;


    public Mono<ConMultidimension> createMultidimensionEmployee(Integer idEmployee) {
        return conMultidimensionRepository.save(ConMultidimension.builder()
                        .tipoMultidimension(Multidimensional.EMPLOYEE.getName())
                        .valorMultidimension(idEmployee)
                .build());
    }
}
