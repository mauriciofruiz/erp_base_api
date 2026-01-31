package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.DepartamentoDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Departamento
import erp.base.api.repository.DepartamentoRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class DepartamentoService(private val tbaDepartamentoRepository: DepartamentoRepository) {

    fun createDepartment(tbaDepartamentoDto: DepartamentoDto): Mono<Departamento> =
        tbaDepartamentoRepository.save(Departamento(null, tbaDepartamentoDto.nombreDepartamento, tbaDepartamentoDto.estadoDepartamento, tbaDepartamentoDto.idAreaGrupo))

    fun updateDepartment(departmentId: Int, tbaDepartamentoDto: DepartamentoDto): Mono<Departamento> =
        tbaDepartamentoRepository.existsById(departmentId).flatMap { exists ->
            if (exists) tbaDepartamentoRepository.save(Departamento(departmentId, tbaDepartamentoDto.nombreDepartamento, tbaDepartamentoDto.estadoDepartamento, tbaDepartamentoDto.idAreaGrupo))
            else Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.DEPARTMENT_NOT_FOUND + departmentId))
        }

    fun getDepartmentById(departmentId: Int): Mono<Departamento> =
        tbaDepartamentoRepository.findById(departmentId)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.DEPARTMENT_NOT_FOUND + departmentId)))

    fun getDepartmentsByParams(name: String?, status: Boolean?, areaGroupId: Int?): Flux<Departamento> {
        val searchByName = name != null && status == null && areaGroupId == null
        val searchByStatus = name == null && status != null && areaGroupId == null
        val searchByGroupArea = name == null && status == null && areaGroupId != null
        return when {
            searchByName -> tbaDepartamentoRepository.findByNombreDepartamentoContaining(name!!)
            searchByStatus -> tbaDepartamentoRepository.findByEstadoDepartamento(status!!)
            searchByGroupArea -> tbaDepartamentoRepository.findByIdAreaGrupo(areaGroupId!!)
            else -> tbaDepartamentoRepository.findAll()
        }
    }

    fun getAllDepartment(): Flux<Departamento> =
        tbaDepartamentoRepository.findAll()
}
