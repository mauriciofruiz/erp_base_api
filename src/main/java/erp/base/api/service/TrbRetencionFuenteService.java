package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TrbRetencionFuenteDto;
import erp.base.api.enums.TipoRetencionEnum;
import erp.base.api.exception.CustomException;
import erp.base.api.models.Retencion;
import erp.base.api.repository.RetencionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TrbRetencionFuenteService {

    private final RetencionRepository retencionRepository;

    /**
     * Método que crea una retención fuente
     *
     * @param trbRetencionFuenteDto objeto que contiene los datos de la retención fuente
     * @return retorna un objeto de tipo Mono TrbRetencionFuente
     */
    public Mono<Retencion> createRetentionSource(TrbRetencionFuenteDto trbRetencionFuenteDto) {
        return retencionRepository.save(Retencion.builder()
                .idCuenta(trbRetencionFuenteDto.idCuenta())
                .detalle(trbRetencionFuenteDto.detalleRetencionFuente())
                .valorPorcentaje(trbRetencionFuenteDto.porcentajeRetencionFuente())
                .porcentajeDescripcion(String.valueOf(trbRetencionFuenteDto.porcentajeRetencionFuente()))
                .campoExtra(String.valueOf(trbRetencionFuenteDto.campoForm03RetencionFuente()))
                .codigoAnexo(trbRetencionFuenteDto.codigoAnexoRetencionFuente())
                .estadoRetencion(trbRetencionFuenteDto.anuladoRetencionFuente())
                .build()
        );
    }

    /**
     * Método que actualiza una retención fuente
     *
     * @param id                    identificador de la retención fuente
     * @param trbRetencionFuenteDto objeto que contiene los datos de la retención fuente
     * @return retorna un objeto de tipo Mono TrbRetencionFuente
     */
    public Mono<Retencion> updateRetentionSource(Integer id, TrbRetencionFuenteDto trbRetencionFuenteDto) {
        return retencionRepository.findById(id).switchIfEmpty(
                Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.RETENTION_SOURCE_NOT_FOUND + id))
        ).flatMap(retencionFuente -> retencionRepository.save(Retencion.builder()
                .idRetencion(id)
                .idCuenta(trbRetencionFuenteDto.idCuenta())
                .detalle(trbRetencionFuenteDto.detalleRetencionFuente())
                .valorPorcentaje(trbRetencionFuenteDto.porcentajeRetencionFuente())
                .porcentajeDescripcion(String.valueOf(trbRetencionFuenteDto.porcentajeRetencionFuente()))
                .campoExtra(String.valueOf(trbRetencionFuenteDto.campoForm03RetencionFuente()))
                .codigoAnexo(trbRetencionFuenteDto.codigoAnexoRetencionFuente())
                .estadoRetencion(trbRetencionFuenteDto.anuladoRetencionFuente())
                .build())
        );
    }

    /**
     * Método que obtiene una retención fuente por ID
     *
     * @param id identificador de la retención fuente
     * @return retorna un objeto de tipo Mono TrbRetencionFuente
     */
    public Mono<Retencion> getRetentionSourceById(Integer id) {
        return retencionRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.RETENTION_SOURCE_NOT_FOUND + id)
                        )
                );
    }

    /**
     * Método que obtiene todas las retenciones en la fuente
     *
     * @return retorna un objeto de tipo Flux TrbRetencionFuente
     */
    public Flux<Retencion> getAllRetentionSource() {
        return retencionRepository.findByEstadoRetencionAndEsProveedorAndIdTipoRetencion(Boolean.TRUE, Boolean.TRUE, TipoRetencionEnum.FUENTE.getId());
    }

    public Flux<Retencion> getAllRetentionSourceClient() {
        return retencionRepository.findByEstadoRetencionAndEsClienteAndIdTipoRetencion(Boolean.TRUE, Boolean.TRUE, TipoRetencionEnum.FUENTE.getId());
    }

}
