package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.TipoCuentaBancariaDto
import erp.base.api.exception.CustomException
import erp.base.api.models.TipoCuentaBancaria
import erp.base.api.repository.TipoCuentaBancariaRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class TipoCuentaBancariaService(private val tbaTipoCuentaBancariaRepository: TipoCuentaBancariaRepository) {

    fun createBankAccountType(bankAccountTypeDto: TipoCuentaBancariaDto): Mono<TipoCuentaBancaria> =
        tbaTipoCuentaBancariaRepository.save(TipoCuentaBancaria(null, bankAccountTypeDto.nombreTipoCuentaBancaria, bankAccountTypeDto.nemonicoTipoCuentaBancaria))

    fun updateBankAccountType(bankAccountTypeId: Int, bankAccountTypeDto: TipoCuentaBancariaDto): Mono<TipoCuentaBancaria> =
        tbaTipoCuentaBancariaRepository.existsById(bankAccountTypeId).flatMap { exists ->
            if (exists) tbaTipoCuentaBancariaRepository.save(TipoCuentaBancaria(bankAccountTypeId, bankAccountTypeDto.nombreTipoCuentaBancaria, bankAccountTypeDto.nemonicoTipoCuentaBancaria))
            else Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BANK_ACCOUNT_TYPE_NOT_FOUND + bankAccountTypeId))
        }

    fun getAllBankAccountType(): Flux<TipoCuentaBancaria> =
        tbaTipoCuentaBancariaRepository.findAll()

    fun getById(bankAccountTypeId: Int): Mono<TipoCuentaBancaria> =
        tbaTipoCuentaBancariaRepository.findById(bankAccountTypeId)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BANK_ACCOUNT_TYPE_NOT_FOUND + bankAccountTypeId)))
}
