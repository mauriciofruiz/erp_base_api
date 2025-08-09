package com.sicon.tba.service;


import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.dto.RrhAreaGrupoDto;
import com.sicon.tba.models.rrh.RrhAreaGrupo;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.repository.rrh.RrhAreaGrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase que contiene los servicios de los grupos de áreas
 */
@Service
@RequiredArgsConstructor
public class RrhAreaGrupoService {

    private final RrhAreaGrupoRepository rrhAreaGrupoRepository;

    /**
     * Método que crea un grupo de área
     * @param groupArea Objeto con los datos del grupo de área
     * @return Objeto con los datos del grupo de área creado
     */
    public Mono<RrhAreaGrupo> createGroupArea(RrhAreaGrupoDto groupArea){
        return rrhAreaGrupoRepository.save(RrhAreaGrupo.builder()
                .nombreAreaGrupo(groupArea.getNombreAreaGrupo())
                .build());
    }

    /**
     * Método que actualiza un grupo de área
     * @param id Id del grupo de área
     * @param groupAreaDto Objeto con los datos del grupo de área
     * @return Objeto con los datos del grupo de área actualizado
     */
    public Mono<RrhAreaGrupo> updateGroupArea(Integer id, RrhAreaGrupoDto groupAreaDto){
        Mono<Boolean> existsAreaGroup = rrhAreaGrupoRepository.existsById(id);
        return existsAreaGroup.flatMap(exists-> exists ? rrhAreaGrupoRepository.save(RrhAreaGrupo.builder()
                .idAreaGrupo(id)
                .nombreAreaGrupo(groupAreaDto.getNombreAreaGrupo())
                .build())
                : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.GROUP_AREA_NOT_FOUND + id)));
    }

    /**
     * Método que obtiene un grupo de área por su id
     * @param id Id del grupo de área
     * @return Objeto con los datos del grupo de área
     */
    public Mono<RrhAreaGrupo> getAreaGroupById(Integer id){
        return rrhAreaGrupoRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.GROUP_AREA_NOT_FOUND + id)));
    }


    /**
     * Método que obtiene los grupos de área por parámetros
     * @param name Nombre del grupo de área
     * @return Lista de objetos con los datos de los grupos de área
     */
    public Flux<RrhAreaGrupo> getAreaGroupByParams(String name){
        return name != null ? rrhAreaGrupoRepository.findByNombreAreaGrupoContaining(name) :rrhAreaGrupoRepository.findAll();
    }
}
