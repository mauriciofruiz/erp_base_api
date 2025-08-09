package com.sicon.tba.service;

import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.dto.TbaRegionDto;
import com.sicon.tba.models.TbaRegion;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.repository.TbaRegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TbaRegionService {

    private final TbaRegionRepository tbaRegionRepository;


    /**
     * Método que crea una region
     *
     * @param regionDto objeto de tipo TbaRegionDto
     * @return objeto de tipo TbaRegion
     */
    public Mono<TbaRegion> createRegion(TbaRegionDto regionDto) {
        return tbaRegionRepository.save(
                TbaRegion.builder()
                        .nombreRegion(regionDto.getNombreRegion())
                        .build()
        );
    }

    /**
     * Método para actualizar una region
     *
     * @param regionId id de la Region a actualizar
     * @return objeto de Tipo TbaRegion
     */

    public Mono<TbaRegion> updateRegion(Integer regionId, TbaRegionDto regionDto) {
        Mono<Boolean> existsRegion = tbaRegionRepository.existsById(regionId);
        return existsRegion.flatMap(existsRegionId -> existsRegionId ?
                tbaRegionRepository.save(TbaRegion.builder()
                        .idRegion(regionId)
                        .nombreRegion(regionDto.getNombreRegion())
                        .build())
                : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.REGION_NOT_FOUND + regionId)));
    }

    /**
     * Método que obtener una region por ID
     *
     * @param regionId id de la region a buscar
     * @return objeto de tipo TbaRegion
     */
    public Mono<TbaRegion> getRegionById(Integer regionId) {
        return tbaRegionRepository.findById(regionId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.REGION_NOT_FOUND + regionId)));
    }

    /**
     * Método para obtener una región por nombre
     * @param  regionName de la región a buscar
     * @return objeto de tipo TbaRegion
     */
    public Flux<TbaRegion> getRegionByName(String regionName) {
        Boolean isRegionExists = (regionName == null);
        return  isRegionExists ? tbaRegionRepository.findAll()
                :tbaRegionRepository.findByNombreRegionContaining(regionName)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.REGION_NAME_NOT_FOUND + regionName)));
    }
}

