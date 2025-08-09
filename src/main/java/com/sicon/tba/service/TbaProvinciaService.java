package com.sicon.tba.service;

import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.dto.TbaProvinciaDto;
import com.sicon.tba.models.TbaProvincia;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.repository.TbaPaisRepository;
import com.sicon.tba.repository.TbaProvinciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase que contiene los servicios de la tabla TbaProvincia
 */
@Service
@RequiredArgsConstructor
public class TbaProvinciaService {

    private final TbaProvinciaRepository tbaProvinciaRepository;

    private final TbaPaisRepository tbaPaisRepository;

    /**
     * Método que crea una provincia
     *
     * @param provinceDto objeto de tipo TbaProvinciaDto
     * @return objeto de tipo TbaProvincia
     */
    public Mono<TbaProvincia> createProvince(TbaProvinciaDto provinceDto) {
        Mono<Boolean> provinceExists = tbaProvinciaRepository.existsById(provinceDto.getIdProvincia());
        Mono<Boolean> countryExists = tbaPaisRepository.existsById(provinceDto.getIdPais());
        return provinceExists.flatMap(existsProvinceId -> existsProvinceId ?
                Mono.error(new CustomException(HttpStatus.CONFLICT, ErrorMessage.PROVINCE_ALREADY_EXISTS + provinceDto.getIdProvincia()))
                :countryExists.flatMap(exists -> exists ? tbaProvinciaRepository.insert(provinceDto.getIdProvincia(), provinceDto.getIdPais(), provinceDto.getNombreProvincia(), provinceDto.getCodRegion())
                : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.COUNTRY_NOT_FOUND + provinceDto.getIdPais()
        )))).then(tbaProvinciaRepository.findById(provinceDto.getIdProvincia()));
    }

    /**
     * Método que actualiza una provincia
     *
     * @param provinceId              id de la provincia
     * @param tbaProvinciaDto objeto de tipo TbaProvinciaDto
     * @return objeto de tipo TbaProvincia
     */
    public Mono<TbaProvincia> updateProvince(String provinceId, TbaProvinciaDto tbaProvinciaDto) {
        Mono<Boolean> provinceExists = tbaProvinciaRepository.existsById(provinceId);
        Mono<Boolean> countryExists = tbaPaisRepository.existsById(tbaProvinciaDto.getIdPais());
        return provinceExists.flatMap(exists -> exists ? countryExists.flatMap(existsC -> existsC ? tbaProvinciaRepository.save(TbaProvincia.builder()
                .idProvincia(provinceId)
                .idPais(tbaProvinciaDto.getIdPais())
                .nombreProvincia(tbaProvinciaDto.getNombreProvincia())
                .codRegion(tbaProvinciaDto.getCodRegion())
                .build())
                : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.COUNTRY_NOT_FOUND + tbaProvinciaDto.getIdPais()
        ))) : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PROVINCE_NOT_FOUND + provinceId)));
    }

    /**
     * Método que consulta una provincia por id
     *
     * @param provinceId id de la provincia
     * @return objeto de tipo TbaProvincia
     */
    public Mono<TbaProvincia> getProvinceById(String provinceId) {
        return tbaProvinciaRepository.findById(provinceId).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PROVINCE_NOT_FOUND + provinceId)));
    }

    public Flux<TbaProvincia> getProvinceByParams(Integer countryId, String provinceName, String regionCode) {
        Boolean isSearchByCountryId = countryId != null && provinceName == null && regionCode == null;
        Boolean isSearchByProvinceName = countryId == null && provinceName != null && regionCode == null;
        Boolean isSearchByCodRegion = countryId == null && provinceName == null && regionCode != null;
        Boolean isSearchByCountryIdAndProvinceName = countryId != null && provinceName != null && regionCode == null;
        Boolean isSearchByCountryIdAndCodRegion = countryId != null && provinceName == null && regionCode != null;
        Boolean isSearchByProvinceNameAndCodRegion = countryId == null && provinceName != null && regionCode != null;
        Boolean isSearchByCountryIdAndProvinceNameAndCodRegion = countryId != null && provinceName != null && regionCode != null;

        if (isSearchByCountryId) {
            return tbaProvinciaRepository.findByIdPais(countryId);
        } else if (isSearchByProvinceName) {
            return tbaProvinciaRepository.findByNombreProvinciaContaining(provinceName);
        } else if (isSearchByCodRegion) {
            return tbaProvinciaRepository.findByCodRegionContaining(regionCode);
        } else if (isSearchByCountryIdAndProvinceName) {
            return tbaProvinciaRepository.findByIdPaisAndNombreProvinciaContaining(countryId, provinceName);
        } else if (isSearchByCountryIdAndCodRegion) {
            return tbaProvinciaRepository.findByIdPaisAndCodRegionContaining(countryId, regionCode);
        } else if (isSearchByProvinceNameAndCodRegion) {
            return tbaProvinciaRepository.findByNombreProvinciaContainingAndCodRegionContaining(provinceName, regionCode);
        } else if (isSearchByCountryIdAndProvinceNameAndCodRegion) {
            return tbaProvinciaRepository.findByIdPaisAndNombreProvinciaContainingAndCodRegionContaining(countryId, provinceName, regionCode);
        } else {
            return tbaProvinciaRepository.findAll();
        }
    }
}
