package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.IdentificacionFiscalDto
import erp.base.api.exception.CustomException
import erp.base.api.models.IdentificacionFiscal
import erp.base.api.repository.IdentificacionFiscalRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class IdentificacionFiscalService(private val trbIdentificacionFiscalRepository: IdentificacionFiscalRepository) {

    fun createTaxIdentification(trbIdentificacionFiscalDto: IdentificacionFiscalDto): Mono<IdentificacionFiscal> {
        val existsTaxIdentification = trbIdentificacionFiscalRepository.findByCodIdentificacionFiscal(trbIdentificacionFiscalDto.codIdentificacionFiscal!!).hasElement()
        return existsTaxIdentification.flatMap { exists ->
            if (!exists) {
                trbIdentificacionFiscalRepository.save(
                    IdentificacionFiscal(
                        idIdentificacionFiscal = null,
                        codIdentificacionFiscal = trbIdentificacionFiscalDto.codIdentificacionFiscal,
                        nombreIdentificacionFiscal = trbIdentificacionFiscalDto.nombreIdentificacionFiscal,
                        codAtsIdentificacionFiscal = trbIdentificacionFiscalDto.codAtsIdentificacionFiscal
                    )
                )
            } else {
                Mono.error(CustomException(HttpStatus.CONFLICT, ErrorMessage.EXISTING_TAX_IDENTIFICATION_CODE + trbIdentificacionFiscalDto.codIdentificacionFiscal))
            }
        }
    }

    fun updateTaxIdentification(id: Int, trbIdentificacionFiscalDto: IdentificacionFiscalDto): Mono<IdentificacionFiscal> {
        val taxIdentificationExists = trbIdentificacionFiscalRepository.existsById(id)
        val codeTaxIdentificationExists = trbIdentificacionFiscalRepository.findByCodIdentificacionFiscal(trbIdentificacionFiscalDto.codIdentificacionFiscal!!).hasElement()
        return taxIdentificationExists.flatMap { exists ->
            if (exists) {
                codeTaxIdentificationExists.flatMap { existsCode ->
                    if (!existsCode) {
                        trbIdentificacionFiscalRepository.save(
                            IdentificacionFiscal(
                                idIdentificacionFiscal = id,
                                codIdentificacionFiscal = trbIdentificacionFiscalDto.codIdentificacionFiscal,
                                nombreIdentificacionFiscal = trbIdentificacionFiscalDto.nombreIdentificacionFiscal,
                                codAtsIdentificacionFiscal = trbIdentificacionFiscalDto.codAtsIdentificacionFiscal
                            )
                        )
                    } else {
                        Mono.error(CustomException(HttpStatus.CONFLICT, ErrorMessage.EXISTING_TAX_IDENTIFICATION_CODE + trbIdentificacionFiscalDto.codIdentificacionFiscal))
                    }
                }
            } else {
                Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.TAX_IDENTIFICATION_NOT_FOUND + id))
            }
        }
    }

    fun getTaxIdentificationById(id: Int): Mono<IdentificacionFiscal> =
        trbIdentificacionFiscalRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.TAX_IDENTIFICATION_NOT_FOUND + id)))

    fun getAllTaxIdentification(): Flux<IdentificacionFiscal> =
        trbIdentificacionFiscalRepository.findAll()
}
