package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.TarifaIvaDto
import erp.base.api.exception.CustomException
import erp.base.api.models.TarifaIva
import erp.base.api.repository.TarifaIvaRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class TarifaIvaService(private val tarifaIvaRepository: TarifaIvaRepository) {

    fun create(tarifaIvaDto: TarifaIvaDto): Mono<TarifaIva> =
        tarifaIvaRepository.save(
            TarifaIva(
                idTarifaIva = null,
                porcentajeTarifaIva = tarifaIvaDto.porcentajeTarifaIva,
                codigoTarifaIva = tarifaIvaDto.codigoTarifaIva,
                descripcionIva = tarifaIvaDto.descripcionIva,
                idCuentaVenta = tarifaIvaDto.idCuentaVenta,
                idCuentaCompra = tarifaIvaDto.idCuentaCompra,
                estadoTarifaIva = tarifaIvaDto.estadoTarifaIva,
                idUsuarioCreacion = null,
                idUsuarioModificacion = null,
                fechaCreacion = null,
                fechaModificacion = null
            )
        )

    fun update(id: Int, tarifaIvaDto: TarifaIvaDto): Mono<TarifaIva> =
        tarifaIvaRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.TAX_RATE_NOT_FOUND + id)))
            .flatMap {
                tarifaIvaRepository.save(
                    TarifaIva(
                        idTarifaIva = id,
                        porcentajeTarifaIva = tarifaIvaDto.porcentajeTarifaIva,
                        codigoTarifaIva = tarifaIvaDto.codigoTarifaIva,
                        descripcionIva = tarifaIvaDto.descripcionIva,
                        idCuentaVenta = tarifaIvaDto.idCuentaVenta,
                        idCuentaCompra = tarifaIvaDto.idCuentaCompra,
                        estadoTarifaIva = tarifaIvaDto.estadoTarifaIva,
                        idUsuarioCreacion = null,
                        idUsuarioModificacion = null,
                        fechaCreacion = null,
                        fechaModificacion = null
                    )
                )
            }

    fun getById(id: Int): Mono<TarifaIva> =
        tarifaIvaRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.TAX_RATE_NOT_FOUND + id)))

    fun getAllActive(): Flux<TarifaIva> =
        tarifaIvaRepository.findByEstadoTarifaIva(true)
}
