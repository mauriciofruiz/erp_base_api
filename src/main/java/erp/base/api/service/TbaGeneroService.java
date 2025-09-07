package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TbaGeneroDto;
import erp.base.api.models.TbaGenero;
import erp.base.api.exception.CustomException;
import erp.base.api.repository.TbaGeneroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase que contiene los métodos para el manejo de los géneros
 */
@Service
@RequiredArgsConstructor
public class TbaGeneroService {

    private final TbaGeneroRepository tbaGeneroRepository;

    /**
     * Método que crea un género
     * @param genderDto objeto que contiene los datos del género
     * @return Mono<TbaGenero> objeto que contiene los datos del género creado
     */
    public Mono<TbaGenero> createGender(TbaGeneroDto genderDto){
        return tbaGeneroRepository.save(TbaGenero.builder()
                .nombreGenero(genderDto.getNombreGenero())
                .codigoGenero(genderDto.getCodigoGenero())
                .build());
    }

    /**
     * Método que actualiza un género
     * @param genderId identificador del género
     * @param genderDto objeto que contiene los datos del género
     * @return Mono<TbaGenero> objeto que contiene los datos del género actualizado
     */
    public Mono<TbaGenero> updateGender(Integer genderId,TbaGeneroDto genderDto){
        Mono<Boolean> existsGenderById = tbaGeneroRepository.existsById(genderId);
        return existsGenderById.flatMap(existsGender-> existsGender ? tbaGeneroRepository.save(
                TbaGenero.builder()
                        .idGenero(genderId)
                        .nombreGenero(genderDto.getNombreGenero())
                        .codigoGenero(genderDto.getCodigoGenero())
                        .build())
                : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.GENDER_NOT_FOUND + genderId)));
    }

    /**
     * Método que obtiene un género por su identificador
     * @param genderId identificador del género
     * @return Mono<TbaGenero> objeto que contiene los datos del género
     */
    public Mono<TbaGenero> getGenderById(Integer genderId){
        return tbaGeneroRepository.findById(genderId);
    }

    /**
     * Método que obtiene todos los géneros
     * @return Flux<TbaGenero> objeto que contiene los datos de todos los géneros
     */
    public Flux<TbaGenero> getAllGenders(){
        return tbaGeneroRepository.findAll();
    }
}
