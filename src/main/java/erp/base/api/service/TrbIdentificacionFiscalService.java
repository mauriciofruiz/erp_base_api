package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TrbIdentificacionFiscalDto;
import erp.base.api.models.TrbIdentificacionFiscal;
import erp.base.api.exception.CustomException;
import erp.base.api.repository.TrbIdentificacionFiscalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase que contiene los métodos para el manejo de las identificaciones fiscales
 */
@Service
@RequiredArgsConstructor
public class TrbIdentificacionFiscalService {

    private final TrbIdentificacionFiscalRepository trbIdentificacionFiscalRepository;

    /**
     * Método para crear una nueva identificación fiscal
     * @param trbIdentificacionFiscalDto Objeto con los datos de la identificación fiscal
     * @return Objeto con los datos de la identificación fiscal creada
     */
    public Mono<TrbIdentificacionFiscal> createTaxIdentification(TrbIdentificacionFiscalDto trbIdentificacionFiscalDto){
        Mono<Boolean> existsTaxIdentification = trbIdentificacionFiscalRepository.findByCodIdentificacionFiscal(trbIdentificacionFiscalDto.getCodIdentificacionFiscal()).hasElement();
        return existsTaxIdentification.flatMap(exists -> !exists ?trbIdentificacionFiscalRepository.save(TrbIdentificacionFiscal.builder()
                .codIdentificacionFiscal(trbIdentificacionFiscalDto.getCodIdentificacionFiscal())
                .nombreIdentificacionFiscal(trbIdentificacionFiscalDto.getNombreIdentificacionFiscal())
                .codAtsIdentificacionFiscal(trbIdentificacionFiscalDto.getCodAtsIdentificacionFiscal())
                .build())
                : Mono.error(new CustomException(HttpStatus.CONFLICT, ErrorMessage.EXISTING_TAX_IDENTIFICATION_CODE + trbIdentificacionFiscalDto.getCodIdentificacionFiscal()))
                );
    }

    /**
     * Método para actualizar una identificación fiscal
     * @param id Id de la identificación fiscal a actualizar
     * @param trbIdentificacionFiscalDto Objeto con los datos de la identificación fiscal
     * @return Objeto con los datos de la identificación fiscal actualizada
     */
    public Mono<TrbIdentificacionFiscal> updateTaxIdentification(Integer id, TrbIdentificacionFiscalDto trbIdentificacionFiscalDto){
        Mono<Boolean> taxIdentificationExists = trbIdentificacionFiscalRepository.existsById(id);
        Mono<Boolean> codeTaxIdentificationExists = trbIdentificacionFiscalRepository.findByCodIdentificacionFiscal(trbIdentificacionFiscalDto.getCodIdentificacionFiscal()).hasElement();
        return taxIdentificationExists.flatMap(exists -> exists ?
                        codeTaxIdentificationExists.flatMap(
                                existsCode -> !existsCode ?
                                        trbIdentificacionFiscalRepository.save(TrbIdentificacionFiscal.builder()
                                                .idIdentificacionFiscal(id)
                                                .codIdentificacionFiscal(trbIdentificacionFiscalDto.getCodIdentificacionFiscal())
                                                .nombreIdentificacionFiscal(trbIdentificacionFiscalDto.getNombreIdentificacionFiscal())
                                                .codAtsIdentificacionFiscal(trbIdentificacionFiscalDto.getCodAtsIdentificacionFiscal())
                                                .build())
                                        : Mono.error(new CustomException(HttpStatus.CONFLICT, ErrorMessage.EXISTING_TAX_IDENTIFICATION_CODE + trbIdentificacionFiscalDto.getCodIdentificacionFiscal()))
                        )
                        : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.TAX_IDENTIFICATION_NOT_FOUND + id))
                );
    }

    /**
     * Método para consultar una identificación fiscal por su id
     * @param id Id de la identificación fiscal a consultar
     * @return Objeto con los datos de la identificación fiscal consultada
     */
    public Mono<TrbIdentificacionFiscal> getTaxIdentificationById(Integer id){
        return trbIdentificacionFiscalRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.TAX_IDENTIFICATION_NOT_FOUND + id)));
    }

    /**
     * Método para consultar todas las identificaciones fiscales
     * @return Lista con los objetos de las identificaciones fiscales
     */
    public Flux<TrbIdentificacionFiscal> getAllTaxIdentification(){
        return trbIdentificacionFiscalRepository.findAll();
    }
}
