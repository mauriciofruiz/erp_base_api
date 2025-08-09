package com.sicon.tba.service;

import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.dto.TbaCiudadDto;
import com.sicon.tba.models.TbaCiudad;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.repository.TbaCiudadRepository;
import com.sicon.tba.repository.TbaProvinciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase que contiene los servicios de la tabla TbaCiudad
 */
@Service
@RequiredArgsConstructor
public class TbaCiudadService {

    private final TbaCiudadRepository tbaCiudadRepository;

    private final TbaProvinciaRepository tbaProvinciaRepository;

    /**
     * Método que crea una ciudad
     * @param cityDto objeto de tipo TbaCiudadDto
     * @return objeto de tipo TbaCiudad
     */
    public Mono<TbaCiudad> createCity(TbaCiudadDto cityDto) {
        Mono<Boolean> alreadyExistsProvince = tbaProvinciaRepository.existsById(cityDto.getIdProvincia());
        return alreadyExistsProvince.flatMap(exists -> exists ? tbaCiudadRepository.save(TbaCiudad.builder()
                .idProvincia(cityDto.getIdProvincia())
                .nombreCiudad(cityDto.getNombreCiudad())
                .codigoCiudad(cityDto.getCodigoCiudad())

                .build()
        ) : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PROVINCE_NOT_FOUND + cityDto.getIdProvincia())
        ));
    }

    /**
     * Método que actualiza una ciudad
     * @param cityId id de la ciudad
     * @param cityDto objeto de tipo TbaCiudadDto
     * @return objeto de tipo TbaCiudad
     */
    public Mono<TbaCiudad> updateCity(Integer cityId, TbaCiudadDto cityDto) {
        Mono<Boolean> alreadyExistsProvince = tbaProvinciaRepository.existsById(cityDto.getIdProvincia());
        Mono<Boolean> alreadyExistsCity = tbaCiudadRepository.existsById(cityId);
        return alreadyExistsCity.flatMap(existsCity -> existsCity ?
                alreadyExistsProvince.flatMap(exists -> exists ? tbaCiudadRepository.save(TbaCiudad.builder()
                        .idProvincia(cityDto.getIdProvincia())
                        .nombreCiudad(cityDto.getNombreCiudad())
                        .codigoCiudad(cityDto.getCodigoCiudad())

                        .build()
                ) : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PROVINCE_NOT_FOUND + cityDto.getIdProvincia())
                ))
                : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CITY_NOT_FOUND + cityId))
        );
    }

    /**
     * Método que consulta los datos de una ciudad por Id
     * @param cityId id de la ciudad
     * @return objeto de tipo TbaCiudad
     */
    public Mono<TbaCiudad> getCityById(Integer cityId) {
        return tbaCiudadRepository.findById(cityId).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CITY_NOT_FOUND + cityId)));
    }

    /**
     * Método que consulta los datos de una ciudad por parámetros
     * @param provinceId id de la provincia
     * @param cityName nombre de la ciudad
     * @param cityCode código de la ciudad
     * @return objeto de tipo TbaCiudad
     */
    public Flux<TbaCiudad> getCityByParams(String provinceId, String cityName, String cityCode) {
        Boolean isSearchByProvinceId = provinceId != null && cityName == null && cityCode == null;
        Boolean isSearchByCityName = provinceId == null && cityName != null && cityCode == null;
        Boolean isSearchByCityCode = provinceId == null && cityName == null && cityCode != null;
        if(isSearchByProvinceId){
            return tbaCiudadRepository.findByIdProvincia(provinceId);
        } else if(isSearchByCityName){
            return tbaCiudadRepository.findByNombreCiudadContaining(cityName);
        } else if(isSearchByCityCode){
            return tbaCiudadRepository.findByCodigoCiudad(cityCode);
        } else {
            return tbaCiudadRepository.findAll();
        }
    }

    /**
     * Método que consulta la ciudad y la provincia
     * @param parishId id de la provincia
     * @return objeto de tipo TbaCiudadDto
     */

    public Mono<TbaCiudadDto> getCityByIdParish(Integer parishId){
        return  tbaCiudadRepository.getCityByIdParish(parishId).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CITY_NOT_FOUND)));
    }

}
