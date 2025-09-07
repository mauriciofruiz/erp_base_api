package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TbaCargoDto;
import erp.base.api.models.TbaCargo;
import erp.base.api.exception.CustomException;
import erp.base.api.repository.TbaCargoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase que contiene los métodos para el manejo de los cargos
 */
@Service
@AllArgsConstructor
public class TbaCargoService {

    private final TbaCargoRepository tbaCargoRepository;

    /**
     * Método para crear un cargo
     * @param serviceChargeDto Objeto con los datos del cargo
     * @return Objeto con los datos del cargo creado
     */
    public Mono<TbaCargo> createServiceCharge(TbaCargoDto serviceChargeDto){
        return tbaCargoRepository.save(
                TbaCargo.builder()
                        .idDepartamento(serviceChargeDto.getIdDepartamento())
                        .nombreCargo(serviceChargeDto.getNombreCargo())
                        .estadoCargo(serviceChargeDto.getEstadoCargo())
                        .build()
        );
    }

    /**
     * Método para actualizar un cargo
     * @param id Id del cargo a actualizar
     * @param tbaCargoDto Objeto con los datos del cargo
     * @return Objeto con los datos del cargo actualizado
     */
    public Mono<TbaCargo> updateServiceCharge(Integer id, TbaCargoDto tbaCargoDto){
        Mono<Boolean> existsPosition = tbaCargoRepository.existsById(id);
        return existsPosition.flatMap(exists -> exists ?
                         tbaCargoRepository.save(
                                TbaCargo.builder()
                                        .idCargo(id)
                                        .idDepartamento(tbaCargoDto.getIdDepartamento())
                                        .nombreCargo(tbaCargoDto.getNombreCargo())
                                        .estadoCargo(tbaCargoDto.getEstadoCargo())
                                        .build())
                   :  Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.STATUS_NOT_FOUND + id))
        );
    }

    /**
     * Método para obtener un cargo por su id
     * @param serviceChargeId Id del cargo a obtener
     * @return Objeto con los datos del cargo
     */
    public Mono<TbaCargo> getServiceChargeById(Integer serviceChargeId){
        return tbaCargoRepository.findById(serviceChargeId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.STATUS_NOT_FOUND + serviceChargeId)));
    }

    /**
     * Método para obtener todos los cargos
     * @return Lista con los datos de todos los cargos
     */
    public Flux<TbaCargo> getAllServiceCharge(){
        return tbaCargoRepository.findAll();
    }
}
