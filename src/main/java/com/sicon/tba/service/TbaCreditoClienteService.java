package com.sicon.tba.service;

import com.sicon.tba.models.TbaCreditoCliente;
import com.sicon.tba.repository.TbaCreditoClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TbaCreditoClienteService {

    private final TbaCreditoClienteRepository tbaCreditoClienteRepository;

    public Mono<TbaCreditoCliente> saveCredit(TbaCreditoCliente tbaCreditoCliente) {
        return tbaCreditoClienteRepository.save(tbaCreditoCliente);
    }


    public Mono<TbaCreditoCliente> getCreditByClientId(Integer clientId) {
        return tbaCreditoClienteRepository.findByIdCliente(clientId);
    }

}
