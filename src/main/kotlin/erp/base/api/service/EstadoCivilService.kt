package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.EstadoCivilDto
import erp.base.api.exception.CustomException
import erp.base.api.models.EstadoCivil
import erp.base.api.repository.EstadoCivilRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class EstadoCivilService(private val tbaEstadoCivilRepository: EstadoCivilRepository) {

    fun createMaritalStatus(tbaEstadoCivilDto: EstadoCivilDto): Mono<EstadoCivil> =
        tbaEstadoCivilRepository.save(EstadoCivil(null, tbaEstadoCivilDto.nombreEstadoCivil, tbaEstadoCivilDto.codigoEstadoCivil))

    fun updateMaritalStatus(maritalStatusId: Int, tbaEstadoCivilDto: EstadoCivilDto): Mono<EstadoCivil> =
        tbaEstadoCivilRepository.existsById(maritalStatusId).flatMap { exists ->
            if (exists) tbaEstadoCivilRepository.save(EstadoCivil(maritalStatusId, tbaEstadoCivilDto.nombreEstadoCivil, tbaEstadoCivilDto.codigoEstadoCivil))
            else Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.MARITAL_STATUS_NOT_FOUND + maritalStatusId))
        }

    fun getMaritalStatusById(maritalStatusId: Int): Mono<EstadoCivil> =
        tbaEstadoCivilRepository.findById(maritalStatusId)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.MARITAL_STATUS_NOT_FOUND + maritalStatusId)))

    fun getAllMaritalStatus(): Flux<EstadoCivil> =
        tbaEstadoCivilRepository.findAll()
}
