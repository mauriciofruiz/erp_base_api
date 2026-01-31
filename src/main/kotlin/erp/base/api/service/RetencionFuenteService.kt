package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.RetencionFuenteDto
import erp.base.api.enums.TipoRetencionEnum
import erp.base.api.exception.CustomException
import erp.base.api.models.Retencion
import erp.base.api.repository.RetencionRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class RetencionFuenteService(private val retencionRepository: RetencionRepository) {

    fun createRetentionSource(trbRetencionFuenteDto: RetencionFuenteDto): Mono<Retencion> =
        retencionRepository.save(
            Retencion(
                idRetencion = null,
                idTipoRetencion = null,
                idCuenta = trbRetencionFuenteDto.idCuenta,
                detalle = trbRetencionFuenteDto.detalleRetencionFuente,
                valorPorcentaje = trbRetencionFuenteDto.porcentajeRetencionFuente,
                porcentajeDescripcion = trbRetencionFuenteDto.porcentajeRetencionFuente?.toString(),
                campoExtra = trbRetencionFuenteDto.campoForm03RetencionFuente?.toString(),
                codigoAnexo = trbRetencionFuenteDto.codigoAnexoRetencionFuente,
                esProveedor = null,
                esCliente = null,
                estadoRetencion = trbRetencionFuenteDto.anuladoRetencionFuente
            )
        )

    fun updateRetentionSource(id: Int, trbRetencionFuenteDto: RetencionFuenteDto): Mono<Retencion> =
        retencionRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.RETENTION_SOURCE_NOT_FOUND + id)))
            .flatMap {
                retencionRepository.save(
                    Retencion(
                        idRetencion = id,
                        idTipoRetencion = null,
                        idCuenta = trbRetencionFuenteDto.idCuenta,
                        detalle = trbRetencionFuenteDto.detalleRetencionFuente,
                        valorPorcentaje = trbRetencionFuenteDto.porcentajeRetencionFuente,
                        porcentajeDescripcion = trbRetencionFuenteDto.porcentajeRetencionFuente?.toString(),
                        campoExtra = trbRetencionFuenteDto.campoForm03RetencionFuente?.toString(),
                        codigoAnexo = trbRetencionFuenteDto.codigoAnexoRetencionFuente,
                        esProveedor = null,
                        esCliente = null,
                        estadoRetencion = trbRetencionFuenteDto.anuladoRetencionFuente
                    )
                )
            }

    fun getRetentionSourceById(id: Int): Mono<Retencion> =
        retencionRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.RETENTION_SOURCE_NOT_FOUND + id)))

    fun getAllRetentionSource(): Flux<Retencion> =
        retencionRepository.findByEstadoRetencionAndEsProveedorAndIdTipoRetencion(true, true, TipoRetencionEnum.FUENTE.id)

    fun getAllRetentionSourceClient(): Flux<Retencion> =
        retencionRepository.findByEstadoRetencionAndEsClienteAndIdTipoRetencion(true, true, TipoRetencionEnum.FUENTE.id)
}
