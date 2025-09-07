package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TbaFormaPagoCobroDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TbaFormaPagoCobro;
import erp.base.api.repository.TbaFormaPagoCobroRepository;
import erp.base.api.utils.Paginacion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TbaFormaPagoCobroService {

    private final TbaFormaPagoCobroRepository tbaFormaPagoCobroRepository;

    public Mono<TbaFormaPagoCobro> createPaymentCollectionMethod(TbaFormaPagoCobroDto tbaFormaPagoCobroDto) {
        Mono<Boolean> existsPaymentCollectionMethod = tbaFormaPagoCobroRepository.existsById(tbaFormaPagoCobroDto.idFormaPagoCobro());
        return existsPaymentCollectionMethod.flatMap(
                exists -> exists
                        ? Mono.error(new CustomException(HttpStatus.BAD_REQUEST, ErrorMessage.PAYMENT_COLLECTION_METHOD_ALREADY_EXISTS + tbaFormaPagoCobroDto.idFormaPagoCobro()))
                        : tbaFormaPagoCobroRepository.createPaymentCollectionMethod(tbaFormaPagoCobroDto.idFormaPagoCobro(),
                        tbaFormaPagoCobroDto.nombreFormaPagoCobro(),
                        tbaFormaPagoCobroDto.aliasFormaPagoCobro(),
                        tbaFormaPagoCobroDto.descuentoFormaPagoCobro(),
                        tbaFormaPagoCobroDto.idFormaPagoCobro(),
                        tbaFormaPagoCobroDto.estadoFormaPagoCobro()).then(tbaFormaPagoCobroRepository.findById(tbaFormaPagoCobroDto.idFormaPagoCobro()))
        );
    }

    public Mono<TbaFormaPagoCobro> updatePaymentCollectionMethod(String id, TbaFormaPagoCobroDto tbaFormaPagoCobroDto) {
        return tbaFormaPagoCobroRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PAYMENT_COLLECTION_METHOD_NOT_FOUND + id))
                ).flatMap(tbaFormaPagoCobro -> {
                    tbaFormaPagoCobro.setNombreFormaPagoCobro(tbaFormaPagoCobroDto.nombreFormaPagoCobro());
                    tbaFormaPagoCobro.setAliasFormaPagoCobro(tbaFormaPagoCobroDto.aliasFormaPagoCobro());
                    tbaFormaPagoCobro.setDescuentoFormaPagoCobro(tbaFormaPagoCobroDto.descuentoFormaPagoCobro());
                    tbaFormaPagoCobro.setCodigoFormaPagoCobro(tbaFormaPagoCobroDto.idFormaPagoCobro());
                    tbaFormaPagoCobro.setEstadoFormaPagoCobro(tbaFormaPagoCobroDto.estadoFormaPagoCobro());
                    return tbaFormaPagoCobroRepository.save(tbaFormaPagoCobro);
                });
    }

    public Mono<TbaFormaPagoCobro> getPaymentCollectionMethod(String id) {
        return tbaFormaPagoCobroRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PAYMENT_COLLECTION_METHOD_NOT_FOUND + id))
                );
    }

    public Flux<TbaFormaPagoCobro> getAllPaymentCollectionMethod() {
        return tbaFormaPagoCobroRepository.findByEstadoFormaPagoCobro(true);
    }

    public Mono<Paginacion<TbaFormaPagoCobro>> getRequestBySearchParams(
            Integer page,
            Integer limit,
            String sortDirection,
            String sortfield
    ) {
        Flux<TbaFormaPagoCobro> formaPagoCobroFlux = tbaFormaPagoCobroRepository.getBySearchParams(sortfield, sortDirection, limit, (page - 1) * limit);
        Mono<Long> total = tbaFormaPagoCobroRepository.getCountBySearchParams();
        return total.flatMap(totalCount -> formaPagoCobroFlux.collectList().map(formaPagoCobros -> new Paginacion<>(formaPagoCobros, page, limit, totalCount.intValue())));
    }
}
