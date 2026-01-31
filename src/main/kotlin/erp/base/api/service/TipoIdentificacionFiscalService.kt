package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.TipoIdentificacionFiscalDto
import erp.base.api.exception.CustomException
import erp.base.api.models.TipoIdentificacionFiscal
import erp.base.api.repository.TipoIdentificacionFiscalRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class TipoIdentificacionFiscalService(private val trbTipoIdentificacionFiscalRepository: TipoIdentificacionFiscalRepository) {

    fun createFiscalIdentificationType(trbTipoIdentificacionFiscalDto: TipoIdentificacionFiscalDto): Mono<TipoIdentificacionFiscal> =
        trbTipoIdentificacionFiscalRepository.existsById(trbTipoIdentificacionFiscalDto.idTipoIdFiscal!!).flatMap { exists ->
            if (exists) Mono.error(CustomException(HttpStatus.BAD_REQUEST, ErrorMessage.PAYMENT_COLLECTION_METHOD_ALREADY_EXISTS + trbTipoIdentificacionFiscalDto.idTipoIdFiscal))
            else trbTipoIdentificacionFiscalRepository.createFiscalIdentificationType(
                trbTipoIdentificacionFiscalDto.idTipoIdFiscal!!,
                trbTipoIdentificacionFiscalDto.tipoTransaccionTipoIdFiscal!!,
                trbTipoIdentificacionFiscalDto.tipoIdentificacionTipoIdFiscal!!
            ).then(trbTipoIdentificacionFiscalRepository.findById(trbTipoIdentificacionFiscalDto.idTipoIdFiscal!!))
        }

    fun updateFiscalIdentificationType(id: String, trbTipoIdentificacionFiscalDto: TipoIdentificacionFiscalDto): Mono<TipoIdentificacionFiscal> =
        trbTipoIdentificacionFiscalRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.FISCAL_IDENTIFICATION_TYPE_ALREADY_EXISTS + id)))
            .flatMap { entity ->
                trbTipoIdentificacionFiscalRepository.save(entity.copy(
                    idTipoIdFiscal = trbTipoIdentificacionFiscalDto.idTipoIdFiscal!!,
                    tipoTransaccionTipoIdFiscal = trbTipoIdentificacionFiscalDto.tipoTransaccionTipoIdFiscal,
                    tipoIdentificacionTipoIdFiscal = trbTipoIdentificacionFiscalDto.tipoIdentificacionTipoIdFiscal
                ))
            }

    fun getFiscalIdentificationType(id: String): Mono<TipoIdentificacionFiscal> =
        trbTipoIdentificacionFiscalRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.FISCAL_IDENTIFICATION_TYPE_NOT_FOUND + id)))

    fun getAllFiscalIdentificationType(): Flux<TipoIdentificacionFiscal> =
        trbTipoIdentificacionFiscalRepository.findAll()
}
