package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TbaPaisDto;
import erp.base.api.models.TbaPais;
import erp.base.api.exception.CustomException;
import erp.base.api.repository.TbaPaisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase que contiene los métodos para el manejo de los países
 */
@Service
@RequiredArgsConstructor
public class TbaPaisService {
    private final TbaPaisRepository tbaPaisRepository;

    /**
     * Método que crea un país
     * @param countryDto objeto que contiene los datos del país
     * @return Mono<TbaPais> objeto que contiene los datos del país creado
     */
    public Mono<TbaPais> createCountry(TbaPaisDto countryDto){
        return tbaPaisRepository.save(TbaPais.builder()
                .codigoPais(countryDto.getCodigoPais())
                .nombrePais(countryDto.getNombrePais())
                .build());
    }

    /**
     * Método que actualiza un país
     * @param countryId identificador del país
     * @param countryDto objeto que contiene los datos del país
     * @return  Mono<TbaPais> objeto que contiene los datos del país actualizado
     */
    public Mono<TbaPais> updateCountry(Integer countryId,TbaPaisDto countryDto ){
        Mono<Boolean> existsById = tbaPaisRepository.existsById(countryId);
        return existsById.flatMap(existsId-> existsId ? tbaPaisRepository.save(TbaPais.builder()
                .idPais(countryId)
                .codigoPais(countryDto.getCodigoPais())
                .nombrePais(countryDto.getNombrePais())
                .build()) : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.COUNTRY_NOT_FOUND + countryId)));
    }

    /**
     * Método que obtiene un país por su identificador
     * @param id identificador del país
     * @return Mono<TbaPais> objeto que contiene los datos del país
     */
    public Mono<TbaPais> getCountryById(Integer id){
        return tbaPaisRepository.findById(id);
    }

    /**
     * Método que obtiene todos los países
     * @return Flux<TbaPais> objeto que contiene los datos de todos los países
     */
    public Flux<TbaPais> getAllCountries(){
        return tbaPaisRepository.findAll();
    }
}
