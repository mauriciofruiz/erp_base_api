package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TbaParroquiaDto;
import erp.base.api.models.TbaParroquia;
import erp.base.api.exception.CustomException;
import erp.base.api.repository.TbaCiudadRepository;
import erp.base.api.repository.TbaParroquiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase que contiene los métodos para la gestión de parroquias
 */
@Service
@RequiredArgsConstructor
public class TbaParroquiaService {
    private final TbaParroquiaRepository tbaParroquiaRepository;

    private final TbaCiudadRepository tbaCiudadRepository;

    /**
     * Método que crea una parroquia
     * @param parishDto objeto que contiene los datos de la parroquia
     * @return Mono<TbaParroquia> objeto que contiene los datos de la parroquia creada
     */
    public Mono<TbaParroquia> createParish(TbaParroquiaDto parishDto) {
        Mono<Boolean> existsCityById = tbaCiudadRepository.existsById(parishDto.getIdCiudad());
        return existsCityById.flatMap(exists -> exists ? tbaParroquiaRepository.save(TbaParroquia.builder()
                .idCiudad(parishDto.getIdCiudad())
                .nombreParroquia(parishDto.getNombreParroquia())
                .codParroquia(parishDto.getCodParroquia())
                .build())
                : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CITY_NOT_FOUND + parishDto.getIdCiudad())));
    }

    /**
     * Método que actualiza una parroquia
     * @param parishId identificador de la parroquia
     * @param parishDto objeto que contiene los datos de la parroquia
     * @return Mono<TbaParroquia> objeto que contiene los datos de la parroquia actualizada
     */
    public Mono<TbaParroquia> updateParish(Integer parishId, TbaParroquiaDto parishDto){
        Mono<Boolean> isCityExistsById = tbaCiudadRepository.existsById(parishDto.getIdCiudad());
        Mono<Boolean> isParishExistsById = tbaParroquiaRepository.existsById(parishId);
        return isCityExistsById.flatMap(cityExists -> cityExists ? isParishExistsById.flatMap(existsParish -> existsParish ? tbaParroquiaRepository.save(TbaParroquia.builder()
                .idParroquia(parishId)
                .idCiudad(parishDto.getIdCiudad())
                .nombreParroquia(parishDto.getNombreParroquia())
                .codParroquia(parishDto.getCodParroquia())
                .build())
                : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PARISH_NOT_FOUND + parishId)))
                : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CITY_NOT_FOUND + parishDto.getIdCiudad())));
    }

    /**
     * Método que obtiene una parroquia por su id
     * @param parishId identificador de la parroquia
     * @return Mono<TbaParroquia> objeto que contiene los datos de la parroquia
     */
    public Mono<TbaParroquia> getParishById(Integer parishId){
        return tbaParroquiaRepository.findById(parishId);
    }

    /**
     * Método que obtiene todas las parroquias
     * @return Flux<TbaParroquia> objeto que contiene los datos de las parroquias
     */
    public Flux<TbaParroquia> getAllParishes(){
        return tbaParroquiaRepository.findAll();
    }

    /**
     * Método que obtiene una parroquia por id.
     *
     * @param parishName nombre de la provincia
     * @return parroquia
     */
    public Flux<TbaParroquiaDto> getParish(String parishName){
        Flux<TbaParroquiaDto> parishDtoFlux = tbaParroquiaRepository.findParishByName(parishName);
        return parishDtoFlux.switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PARISH_NOT_FOUND)));
    }

    public Flux<TbaParroquia> getParishByCityId(Integer cityId){
        return tbaParroquiaRepository.findByIdCiudad(cityId);
    }
}
