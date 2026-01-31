package erp.base.api.handler

import erp.base.api.dto.DepartamentoDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Departamento
import erp.base.api.service.DepartamentoService
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class DepartamentoHandler(
    private val tbaDepartamentoService: DepartamentoService,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun create(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(DepartamentoDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(tbaDepartamentoService.createDepartment(dto), Departamento::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun update(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return request.bodyToMono(DepartamentoDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(tbaDepartamentoService.updateDepartment(id, dto), Departamento::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaDepartamentoService.getDepartmentById(id), Departamento::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getByParams(request: ServerRequest): Mono<ServerResponse> {
        val nameParam = request.queryParam("name").orElse(null)
        val statusParam = request.queryParam("status").orElse(null)
        val areaGroupIdParam = request.queryParam("areaGroupId").orElse(null)
        val status = statusParam?.toBoolean()
        val areaGroupId = areaGroupIdParam?.toIntOrNull()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaDepartamentoService.getDepartmentsByParams(nameParam, status, areaGroupId), Departamento::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getAllDepartment(request: ServerRequest): Mono<ServerResponse> {
        val departamentoFlux: Flux<Departamento> = tbaDepartamentoService.getAllDepartment()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(departamentoFlux, Departamento::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }
}
