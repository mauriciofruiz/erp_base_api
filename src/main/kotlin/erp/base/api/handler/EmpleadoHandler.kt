package erp.base.api.handler

import erp.base.api.dto.CuentaDatosContablesDto
import erp.base.api.dto.EmpleadoDto
import erp.base.api.dto.EmpleadoFichaDto
import erp.base.api.dto.EmpleadoPersonaDto
import erp.base.api.dto.EmployeeInformationDto
import erp.base.api.dto.InformacionContractualDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Empleado
import erp.base.api.service.EmpleadoService
import erp.base.api.utils.Utils
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class EmpleadoHandler(
    private val empleadoService: EmpleadoService,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun createEmployee(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(EmployeeInformationDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { employee ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(empleadoService.createEmployeePerson(employee.empleadoDto!!, employee.informacionContractualDto!!), Empleado::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun getFichaEmpleado(request: ServerRequest): Mono<ServerResponse> {
        val paramSearch = request.queryParam("param").orElse(null)
        val identification: String? = if (paramSearch != null && Utils.validateNumbers(paramSearch)) paramSearch else null
        val completeName: String? = if (paramSearch != null && !Utils.validateNumbers(paramSearch)) paramSearch else null
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(empleadoService.getEmployee(completeName, identification), EmpleadoFichaDto::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getCompleteEmployee(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(empleadoService.getEmployeeRecord(id), Empleado::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getContractualInformation(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(empleadoService.getContractualInformation(id), InformacionContractualDto::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun updateInformation(request: ServerRequest): Mono<ServerResponse> {
        val idEmpleado = request.pathVariable("id").toInt()
        return request.bodyToMono(EmpleadoDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { empleado ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(empleadoService.updateEmployeeRecord(idEmpleado, empleado), Empleado::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun updateContractualInformation(request: ServerRequest): Mono<ServerResponse> {
        val idEmpleado = request.pathVariable("id").toInt()
        return request.bodyToMono(InformacionContractualDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { informationContractual ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(empleadoService.updateContractualInformation(idEmpleado, informationContractual), Empleado::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getAccountingDataEmployee(request: ServerRequest): Mono<ServerResponse> {
        val idEmpleado = request.pathVariable("id").toInt()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(empleadoService.getAccountingDataEmployee(idEmpleado), CuentaDatosContablesDto::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getAllEmployeeActive(request: ServerRequest): Mono<ServerResponse> {
        val paramSearch = request.queryParam("param").orElse(null)
        return if (paramSearch != null) {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(empleadoService.getEmployeeActiveByParam(paramSearch), EmpleadoPersonaDto::class.java)
        } else {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(empleadoService.getEmployeeActive(), EmpleadoPersonaDto::class.java)
                .onErrorMap { e ->
                    log.error(e.message)
                    CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
                }
        }
    }

    fun getEmpleadoPersonaById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.queryParam("id").orElse("0").toInt()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(empleadoService.getEmpleadoPersonaById(id), EmpleadoPersonaDto::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }
}
