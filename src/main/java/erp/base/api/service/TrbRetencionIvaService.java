package erp.base.api.service;


import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TrbRetencionIvaDto;
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
public class TrbRetencionIvaService {

    private final RetencionRepository retencionRepository;

    public Mono<Retencion> createRetentionIva(TrbRetencionIvaDto trbRetencionIvaDto) {
        return retencionRepository.save(Retencion.builder()
                .idCuenta(trbRetencionIvaDto.idCuenta())
                .codigoAnexo(trbRetencionIvaDto.codigoRetencionIva())
                .detalle(trbRetencionIvaDto.descripcionRetencionIva())
                .valorPorcentaje(trbRetencionIvaDto.porcentajeRetencionIva())
                .porcentajeDescripcion(String.valueOf(trbRetencionIvaDto.porcentajeRetencionIva()))
                .estadoRetencion(trbRetencionIvaDto.anuladoRetencionIva())
                .build());
    }

    public Mono<Retencion> updateRetentionIva(Integer id, TrbRetencionIvaDto trbRetencionIvaDto) {
        return retencionRepository.findById(id).switchIfEmpty(
                        Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.RETENTION_IVA_NOT_FOUND + id)))
                .flatMap(retencion -> retencionRepository.save(
                        Retencion.builder()
                                .idRetencion(id)
                                .idCuenta(trbRetencionIvaDto.idCuenta())
                                .codigoAnexo(trbRetencionIvaDto.codigoRetencionIva())
                                .detalle(trbRetencionIvaDto.descripcionRetencionIva())
                                .valorPorcentaje(trbRetencionIvaDto.porcentajeRetencionIva())
                                .porcentajeDescripcion(String.valueOf(trbRetencionIvaDto.porcentajeRetencionIva()))
                                .estadoRetencion(trbRetencionIvaDto.anuladoRetencionIva())
                                .build()
                ));
    }

    public Mono<Retencion> getRetentionIva(Integer id) {
        return retencionRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.RETENTION_IVA_NOT_FOUND + id))
                );
    }

    public Flux<Retencion> getAllRetentionIva() {
        return retencionRepository.findByEstadoRetencionAndEsProveedorAndIdTipoRetencion(Boolean.TRUE, Boolean.TRUE, TipoRetencionEnum.IVA.getId());
    }

    public Flux<Retencion> getAllRetentionIvaCliente() {
        return retencionRepository.findByEstadoRetencionAndEsClienteAndIdTipoRetencion(Boolean.TRUE, Boolean.TRUE, TipoRetencionEnum.IVA.getId());
    }
}
