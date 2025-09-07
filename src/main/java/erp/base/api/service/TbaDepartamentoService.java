package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TbaDepartamentoDto;
import erp.base.api.models.TbaDepartamento;
import erp.base.api.exception.CustomException;
import erp.base.api.repository.TbaDepartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase que contiene los servicios de los departamentos
 */
@Service
@RequiredArgsConstructor
public class TbaDepartamentoService {

    private final TbaDepartamentoRepository tbaDepartamentoRepository;
    /**
     * Método que crea un departamento
     * @param tbaDepartamentoDto Objeto con los datos del departamento
     * @return Objeto con los datos del departamento creado
     */
    public Mono<TbaDepartamento> createDepartment(TbaDepartamentoDto tbaDepartamentoDto){
        return tbaDepartamentoRepository.save(TbaDepartamento.builder()
                .estadoDepartamento(tbaDepartamentoDto.getEstadoDepartamento())
                .nombreDepartamento(tbaDepartamentoDto.getNombreDepartamento())
                .idAreaGrupo(tbaDepartamentoDto.getIdAreaGrupo())
                .build());
    }

    /**
     * Método que actualiza un departamento
     * @param departmentId Id del departamento
     * @param tbaDepartamentoDto Objeto con los datos del departamento
     * @return Objeto con los datos del departamento actualizado
     */
    public Mono<TbaDepartamento> updateDepartment(Integer departmentId, TbaDepartamentoDto tbaDepartamentoDto){
        Mono<Boolean> departmentExists = tbaDepartamentoRepository.existsById(departmentId);
        return departmentExists.flatMap(exists-> exists  ?
                        tbaDepartamentoRepository.save(TbaDepartamento.builder()
                                .idDepartamento(departmentId)
                                .estadoDepartamento(tbaDepartamentoDto.getEstadoDepartamento())
                                .nombreDepartamento(tbaDepartamentoDto.getNombreDepartamento())
                                .idAreaGrupo(tbaDepartamentoDto.getIdAreaGrupo())
                                .build())
                : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.DEPARTMENT_NOT_FOUND + departmentId))
        );
    }

    /**
     * Método que obtiene un departamento por su id
     * @param departmentId Id del departamento
     * @return Objeto con los datos del departamento
     */
   public Mono<TbaDepartamento> getDepartmentById(Integer departmentId){
          return tbaDepartamentoRepository.findById(departmentId)
                  .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.DEPARTMENT_NOT_FOUND + departmentId)));
   }


    /**
     * Método que obtiene los departamentos por los parámetros
     * @param name Nombre del departamento
     * @param status Estado del departamento
     * @return Lista de departamentos
     */
   public Flux<TbaDepartamento> getDepartmentsByParams(String name, Boolean status, Integer areaGroupId){
      boolean searchByName = name != null && status == null && areaGroupId == null;
      boolean searchByStatus = name == null && status != null && areaGroupId == null;
      boolean searchByGroupArea = name == null && status == null && areaGroupId != null;
      if(searchByName){
            return tbaDepartamentoRepository.findByNombreDepartamentoContaining(name);
      }else if(searchByStatus){
            return tbaDepartamentoRepository.findByEstadoDepartamento(status);
      }else if(searchByGroupArea) {
          return tbaDepartamentoRepository.findByIdAreaGrupo(areaGroupId);
      }else{
          return tbaDepartamentoRepository.findAll();
      }
   }
    /**
     * Método que obtiene los departamentos
     * @return Lista de departamentos
     */

    public Flux<TbaDepartamento> getAllDepartment(){
        return tbaDepartamentoRepository.findAll();
    }


}
