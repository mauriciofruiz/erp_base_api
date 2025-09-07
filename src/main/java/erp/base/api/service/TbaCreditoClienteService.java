package erp.base.api.service;

import erp.base.api.models.TbaCreditoCliente;
import erp.base.api.repository.TbaCreditoClienteRepository;
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
