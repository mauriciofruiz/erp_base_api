package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.AreaGrupoDto
import erp.base.api.exception.CustomException
import erp.base.api.models.rrh.AreaGrupo
import erp.base.api.repository.rrh.AreaGrupoRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class AreaGrupoService(private val rrhAreaGrupoRepository: AreaGrupoRepository) {

    fun createGroupArea(groupArea: AreaGrupoDto): Mono<AreaGrupo> =
        rrhAreaGrupoRepository.save(AreaGrupo(null, groupArea.nombreAreaGrupo))

    fun updateGroupArea(id: Int, groupAreaDto: AreaGrupoDto): Mono<AreaGrupo> =
        rrhAreaGrupoRepository.existsById(id).flatMap { exists ->
            if (exists) rrhAreaGrupoRepository.save(AreaGrupo(id, groupAreaDto.nombreAreaGrupo))
            else Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.GROUP_AREA_NOT_FOUND + id))
        }

    fun getAreaGroupById(id: Int): Mono<AreaGrupo> =
        rrhAreaGrupoRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.GROUP_AREA_NOT_FOUND + id)))

    fun getAreaGroupByParams(name: String?): Flux<AreaGrupo> =
        if (name != null) rrhAreaGrupoRepository.findByNombreAreaGrupoContaining(name) else rrhAreaGrupoRepository.findAll()
}
