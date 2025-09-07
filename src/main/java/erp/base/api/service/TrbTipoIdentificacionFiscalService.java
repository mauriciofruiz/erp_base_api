package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TrbTipoIdentificacionFiscalDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TrbTipoIdentificacionFiscal;
import erp.base.api.repository.TrbTipoIdentificacionFiscalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TrbTipoIdentificacionFiscalService {

    private final TrbTipoIdentificacionFiscalRepository trbTipoIdentificacionFiscalRepository;

    public Mono<TrbTipoIdentificacionFiscal> createFiscalIdentificationType(TrbTipoIdentificacionFiscalDto trbTipoIdentificacionFiscalDto) {
        Mono<Boolean> existsFiscalIdentificationType = trbTipoIdentificacionFiscalRepository.existsById(trbTipoIdentificacionFiscalDto.idTipoIdFiscal());
        return existsFiscalIdentificationType.flatMap(
                exists -> exists
                        ? Mono.error(new CustomException(HttpStatus.BAD_REQUEST, ErrorMessage.PAYMENT_COLLECTION_METHOD_ALREADY_EXISTS + trbTipoIdentificacionFiscalDto.idTipoIdFiscal()))
                        : trbTipoIdentificacionFiscalRepository.createFiscalIdentificationType(
                                trbTipoIdentificacionFiscalDto.idTipoIdFiscal(),
                                trbTipoIdentificacionFiscalDto.tipoTransaccionTipoIdFiscal(),
                                trbTipoIdentificacionFiscalDto.tipoIdentificacionTipoIdFiscal()
                        )
                        .then(trbTipoIdentificacionFiscalRepository.findById(trbTipoIdentificacionFiscalDto.idTipoIdFiscal()))
        );
    }

    public Mono<TrbTipoIdentificacionFiscal> updateFiscalIdentificationType(String id, TrbTipoIdentificacionFiscalDto trbTipoIdentificacionFiscalDto) {
        return trbTipoIdentificacionFiscalRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.FISCAL_IDENTIFICATION_TYPE_ALREADY_EXISTS + id))
                ).flatMap(trbTipoIdentificacionFiscal -> {
                    trbTipoIdentificacionFiscal.setIdTipoIdFiscal(trbTipoIdentificacionFiscalDto.idTipoIdFiscal());
                    trbTipoIdentificacionFiscal.setTipoTransaccionTipoIdFiscal(trbTipoIdentificacionFiscalDto.tipoTransaccionTipoIdFiscal());
                    trbTipoIdentificacionFiscal.setTipoIdentificacionTipoIdFiscal(trbTipoIdentificacionFiscalDto.tipoIdentificacionTipoIdFiscal());
                    return trbTipoIdentificacionFiscalRepository.save(trbTipoIdentificacionFiscal);
                });
    }

    public Mono<TrbTipoIdentificacionFiscal> getFiscalIdentificationType(String id) {
        return trbTipoIdentificacionFiscalRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.FISCAL_IDENTIFICATION_TYPE_NOT_FOUND + id))
                );
    }

    public Flux<TrbTipoIdentificacionFiscal> getAllFiscalIdentificationType() {
        return trbTipoIdentificacionFiscalRepository.findAll();
    }
}
