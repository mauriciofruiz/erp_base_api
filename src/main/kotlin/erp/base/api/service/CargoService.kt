package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.CargoDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Cargo
import erp.base.api.repository.CargoRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CargoService(private val tbaCargoRepository: CargoRepository) {

    fun createServiceCharge(serviceChargeDto: CargoDto): Mono<Cargo> =
        tbaCargoRepository.save(
            Cargo(
                null,
                serviceChargeDto.idDepartamento,
                serviceChargeDto.nombreCargo,
                serviceChargeDto.estadoCargo
            )
        )

    fun updateServiceCharge(id: Int, tbaCargoDto: CargoDto): Mono<Cargo> =
        tbaCargoRepository.existsById(id).flatMap { exists ->
            if (exists) tbaCargoRepository.save(Cargo(id, tbaCargoDto.idDepartamento, tbaCargoDto.nombreCargo, tbaCargoDto.estadoCargo))
            else Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.STATUS_NOT_FOUND + id))
        }

    fun getServiceChargeById(serviceChargeId: Int): Mono<Cargo> =
        tbaCargoRepository.findById(serviceChargeId)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.STATUS_NOT_FOUND + serviceChargeId)))

    fun getAllServiceCharge(): Flux<Cargo> =
        tbaCargoRepository.findAll()
}
