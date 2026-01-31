package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.RetencionIvaDto
import erp.base.api.enums.TipoRetencionEnum
import erp.base.api.exception.CustomException
import erp.base.api.models.Retencion
import erp.base.api.repository.RetencionRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class RetencionIvaService(private val retencionRepository: RetencionRepository) {

    fun createRetentionIva(trbRetencionIvaDto: RetencionIvaDto): Mono<Retencion> =
        retencionRepository.save(
            Retencion(
                idRetencion = null,
                idTipoRetencion = null,
                idCuenta = trbRetencionIvaDto.idCuenta,
                detalle = trbRetencionIvaDto.descripcionRetencionIva,
                valorPorcentaje = trbRetencionIvaDto.porcentajeRetencionIva,
                porcentajeDescripcion = trbRetencionIvaDto.porcentajeRetencionIva?.toString(),
                campoExtra = null,
                codigoAnexo = trbRetencionIvaDto.codigoRetencionIva,
                esProveedor = null,
                esCliente = null,
                estadoRetencion = trbRetencionIvaDto.anuladoRetencionIva
            )
        )

    fun updateRetentionIva(id: Int, trbRetencionIvaDto: RetencionIvaDto): Mono<Retencion> =
        retencionRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.RETENTION_IVA_NOT_FOUND + id)))
            .flatMap {
                retencionRepository.save(
                    Retencion(
                        idRetencion = id,
                        idTipoRetencion = null,
                        idCuenta = trbRetencionIvaDto.idCuenta,
                        detalle = trbRetencionIvaDto.descripcionRetencionIva,
                        valorPorcentaje = trbRetencionIvaDto.porcentajeRetencionIva,
                        porcentajeDescripcion = trbRetencionIvaDto.porcentajeRetencionIva?.toString(),
                        campoExtra = null,
                        codigoAnexo = trbRetencionIvaDto.codigoRetencionIva,
                        esProveedor = null,
                        esCliente = null,
                        estadoRetencion = trbRetencionIvaDto.anuladoRetencionIva
                    )
                )
            }

    fun getRetentionIva(id: Int): Mono<Retencion> =
        retencionRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.RETENTION_IVA_NOT_FOUND + id)))

    fun getAllRetentionIva(): Flux<Retencion> =
        retencionRepository.findByEstadoRetencionAndEsProveedorAndIdTipoRetencion(true, true, TipoRetencionEnum.IVA.id)

    fun getAllRetentionIvaCliente(): Flux<Retencion> =
        retencionRepository.findByEstadoRetencionAndEsClienteAndIdTipoRetencion(true, true, TipoRetencionEnum.IVA.id)
}
