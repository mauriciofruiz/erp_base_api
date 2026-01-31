package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.exception.CustomException
import erp.base.api.models.TipoVehiculo
import erp.base.api.repository.TipoVehiculoRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class TipoVehiculoService(private val tbaTipoVehiculoRepository: TipoVehiculoRepository) {

    fun createVehicleType(tbaTipoVehiculo: TipoVehiculo): Mono<TipoVehiculo> {
        val entity = tbaTipoVehiculo.copy(idTipoVehiculo = null)
        return tbaTipoVehiculoRepository.save(entity)
    }

    fun findAll(): Flux<TipoVehiculo> =
        tbaTipoVehiculoRepository.findAll()

    fun findById(id: Int): Mono<TipoVehiculo> =
        tbaTipoVehiculoRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.VEHICLE_TYPE_NOT_FOUND + id)))

    fun delete(id: Int): Mono<Void> =
        tbaTipoVehiculoRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.VEHICLE_TYPE_NOT_FOUND + id)))
            .flatMap { tbaTipoVehiculoRepository.delete(it) }
}
