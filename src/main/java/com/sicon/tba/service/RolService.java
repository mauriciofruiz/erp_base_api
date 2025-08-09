package com.sicon.tba.service;

import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.Rol;
import com.sicon.tba.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository rolRepository;

    public Mono<Rol> getById(Integer id) {
        return rolRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.ROLE_NOT_FOUND + id)
                        )
                );
    }


    public Flux<Rol> getAll() {
        return rolRepository.findAll();
    }

}
