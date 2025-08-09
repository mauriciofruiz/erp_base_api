package com.sicon.tba.service;

import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.dto.TrbIdentificacionFiscalDto;
import com.sicon.tba.models.TrbIdentificacionFiscal;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.repository.TrbIdentificacionFiscalRepository;
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
