package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TbaTipoCuentaBancariaDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TbaTipoCuentaBancaria;
import erp.base.api.repository.TbaTipoCuentaBancariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TbaTipoCuentaBancariaService {

    private final TbaTipoCuentaBancariaRepository tbaTipoCuentaBancariaRepository;

    /**
     * Método para crear nuevos tipos de Cuentas
     *
     * @return Objeto con los datos del nuevo tipo de cuenta
     * @Param tbaTipoCuentaBancaria Objeto con los datos de la persona
     */

    public Mono<TbaTipoCuentaBancaria> createBankAccountType(TbaTipoCuentaBancariaDto bankAccountTypeDto) {
        return tbaTipoCuentaBancariaRepository.save(TbaTipoCuentaBancaria.builder()
                .nombreTipoCuentaBancaria(bankAccountTypeDto.getNombreTipoCuentaBancaria())
                .nemonicoTipoCuentaBancaria(bankAccountTypeDto.getNemonicoTipoCuentaBancaria())
                .build());
    }

    /**
     * Método para actualizar nuevos tipos de Cuentas
     *
     * @return Objeto con los datos del nuevo tipo de cuenta
     * @Param id Id del tipoCuentaBancaria
     * @Param tbaTipoCuentaBancaria Objeto con los datos del tipo de cuenta
     */

    public Mono<TbaTipoCuentaBancaria> updateBankAccountType(Integer bankAccountTypeId, TbaTipoCuentaBancariaDto bankAccountTypeDto) {
        Mono<Boolean> bankAccountTypeExists = tbaTipoCuentaBancariaRepository.existsById(bankAccountTypeId);
        return bankAccountTypeExists.flatMap(exists -> exists ?
                tbaTipoCuentaBancariaRepository.save(
                        TbaTipoCuentaBancaria.builder()
                                .idTipoCuentaBancaria(bankAccountTypeId)
                                .nombreTipoCuentaBancaria(bankAccountTypeDto.getNombreTipoCuentaBancaria())
                                .nemonicoTipoCuentaBancaria(bankAccountTypeDto.getNemonicoTipoCuentaBancaria())
                                .build()
                )
                :
                Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BANK_ACCOUNT_TYPE_NOT_FOUND + bankAccountTypeId)));
    }

    /**
     * Método que consulta un tipo de cuenta
     * @param
     * @return Objeto con los datos del tipo de cuenta
     */

    public Flux<TbaTipoCuentaBancaria> getAllBankAccountType(){
        return tbaTipoCuentaBancariaRepository.findAll();
    }

    /**
     * Método que consulta un tipo de cuenta
     *
     * @param bankAccountTypeId Id del tipo de cuenta
     * @return Objeto con los datos del tipo de cuenta
     */

    public Mono<TbaTipoCuentaBancaria> getById(Integer bankAccountTypeId) {
        return tbaTipoCuentaBancariaRepository.findById(bankAccountTypeId).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BANK_ACCOUNT_TYPE_NOT_FOUND + bankAccountTypeId)));
    }

}
