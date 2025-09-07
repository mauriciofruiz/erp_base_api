package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TbaEstadoCivilDto;
import erp.base.api.models.TbaEstadoCivil;
import erp.base.api.exception.CustomException;
import erp.base.api.repository.TbaEstadoCivilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase que contiene los métodos para el manejo de los estados civiles
 */
@Service
@RequiredArgsConstructor
public class TbaEstadoCivilService {

    private final TbaEstadoCivilRepository tbaEstadoCivilRepository;

    /**
     * Método que crea un estado civil
     * @param tbaEstadoCivilDto objeto que contiene los datos del estado civil
     * @return Mono<TbaEstadoCivil> objeto que contiene los datos del estado civil creado
     */
    public Mono<TbaEstadoCivil> createMaritalStatus(TbaEstadoCivilDto tbaEstadoCivilDto){
        return tbaEstadoCivilRepository.save(TbaEstadoCivil.builder()
                .nombreEstadoCivil(tbaEstadoCivilDto.getNombreEstadoCivil())
                .codigoEstadoCivil(tbaEstadoCivilDto.getCodigoEstadoCivil())
                .build());
    }

    /**
     * Método que actualiza un estado civil
     * @param maritalStatusId identificador del estado civil
     * @param tbaEstadoCivilDto objeto que contiene los datos del estado civil
     * @return Mono<TbaEstadoCivil> objeto que contiene los datos del estado civil actualizado
     */
    public Mono<TbaEstadoCivil> updateMaritalStatus(Integer maritalStatusId, TbaEstadoCivilDto tbaEstadoCivilDto){
        Mono<Boolean> maritalStatusExists = tbaEstadoCivilRepository.existsById(maritalStatusId);
        return maritalStatusExists.flatMap(existsMaritalStatus-> existsMaritalStatus ? tbaEstadoCivilRepository.save(TbaEstadoCivil.builder()
                .idEstadoCivil(maritalStatusId)
                .nombreEstadoCivil(tbaEstadoCivilDto.getNombreEstadoCivil())
                .codigoEstadoCivil(tbaEstadoCivilDto.getCodigoEstadoCivil())
                .build())
                : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.MARITAL_STATUS_NOT_FOUND + maritalStatusId))
                );
    }

    /**
     * Método que obtiene un estado civil por su id
     * @param maritalStatusId identificador del estado civil
     * @return Mono<TbaEstadoCivil> objeto que contiene los datos del estado civil
     */
    public Mono<TbaEstadoCivil> getMaritalStatusById(Integer maritalStatusId){
        return tbaEstadoCivilRepository.findById(maritalStatusId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.MARITAL_STATUS_NOT_FOUND + maritalStatusId)));
    }

    /**
     * Método que obtiene todos los estados civiles
     * @return Flux<TbaEstadoCivil> objeto que contiene los datos de todos los estados civiles
     */
    public Flux<TbaEstadoCivil> getAllMaritalStatus(){
        return tbaEstadoCivilRepository.findAll();
    }
}
