package com.sicon.tba.service;

import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.dto.TrbTipoContribuyenteDto;
import com.sicon.tba.models.TrbTipoContribuyente;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.repository.TrbTipoContribuyenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase que contiene los métodos para el manejo de los tipos de contribuyente
 */
@Service
@RequiredArgsConstructor
public class TrbTipoContribuyenteService {

    private final TrbTipoContribuyenteRepository trbTipoContribuyenteRepository;

    /**
     * Método que crea un tipo de contribuyente
     * @param trbTipoContribuyenteDto objeto que contiene los datos del tipo de contribuyente
     * @return Mono<TrbTipoContribuyente> objeto que contiene los datos del tipo de contribuyente creado
     */
    public Mono<TrbTipoContribuyente> createContributorType(TrbTipoContribuyenteDto trbTipoContribuyenteDto){
        Mono<Boolean> contributorTypeExists = trbTipoContribuyenteRepository.existsById(trbTipoContribuyenteDto.idTipoContribuyente());
        return contributorTypeExists.flatMap(exists -> {
            if (exists) {
                return Mono.error(new CustomException(HttpStatus.CONFLICT, ErrorMessage.EXISTING_CONTRIBUTOR_TYPE + trbTipoContribuyenteDto.idTipoContribuyente()));
            } else {
                return trbTipoContribuyenteRepository.createContributorType(
                        trbTipoContribuyenteDto.idTipoContribuyente(),
                        trbTipoContribuyenteDto.nombreTipoContribuyente(),
                        trbTipoContribuyenteDto.codigoTipoContribuyente()
                ).then(trbTipoContribuyenteRepository.findById(trbTipoContribuyenteDto.idTipoContribuyente()));
            }
        });
    }

    /**
     * Método que actualiza un tipo de contribuyente
     * @param contributorTypeId identificador del tipo de contribuyente
     * @param trbTipoContribuyenteDto objeto que contiene los datos del tipo de contribuyente
     * @return Mono<TrbTipoContribuyente> objeto que contiene los datos del tipo de contribuyente actualizado
     */
    public Mono<TrbTipoContribuyente> updateContributorType(String contributorTypeId,TrbTipoContribuyenteDto trbTipoContribuyenteDto){
        Mono<Boolean> contributorTypeExists = trbTipoContribuyenteRepository.existsById(contributorTypeId);
        return contributorTypeExists.flatMap(exists -> exists ? trbTipoContribuyenteRepository.save(
                TrbTipoContribuyente.builder()
                        .idTipoContribuyente(contributorTypeId)
                        .nombreTipoContribuyente(trbTipoContribuyenteDto.nombreTipoContribuyente())
                        .codigoTipoContribuyente(trbTipoContribuyenteDto.codigoTipoContribuyente())
                        .build()
        ) : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CONTRIBUTOR_TYPE_NOT_FOUND + contributorTypeId)));
    }

    /**
     * Método que obtiene un tipo de contribuyente por su identificador
     * @param contributorTypeId identificador del tipo de contribuyente
     * @return Mono<TrbTipoContribuyente> objeto que contiene los datos del tipo de contribuyente
     */
    public Mono<TrbTipoContribuyente> getContributorTypeById(String contributorTypeId){
        return trbTipoContribuyenteRepository.findById(contributorTypeId);
    }

    /**
     * Método que obtiene todos los tipos de contribuyentes
     * @return Flux<TrbTipoContribuyente> objeto que contiene los datos de todos los tipos de contribuyentes
     */
    public Flux<TrbTipoContribuyente> getAllContributorTypes(){
        return trbTipoContribuyenteRepository.findAll();
    }
}
