package erp.base.api.handler

import erp.base.api.constants.Constants
import erp.base.api.dto.response.PersonaClienteCreditoDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Cliente
import erp.base.api.service.ClienteService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ClienteHandler(
    private val clienteService: ClienteService,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun create(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(PersonaClienteCreditoDto::class.java)
            .log()
            .flatMap { dto ->
                ServerResponse.ok().body(clienteService.createCustomer(dto), PersonaClienteCreditoDto::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun update(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return request.bodyToMono(PersonaClienteCreditoDto::class.java)
            .log()
            .flatMap { dto ->
                ServerResponse.ok().body(clienteService.updateClient(id, dto), PersonaClienteCreditoDto::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        val userId = request.headers().firstHeader(Constants.USER_ID_HEADER)!!.toInt()
        return ServerResponse.ok()
            .body(clienteService.findClientById(id, userId), PersonaClienteCreditoDto::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getByParams(request: ServerRequest): Mono<ServerResponse> {
        val nameClientParam = request.queryParam("clientCommercialName").orElse(null)
        val categoryClientIDParam = request.queryParam("clientCategoryID").orElse(null)
        val identificationClientParam = request.queryParam("clientIdentification").orElse(null)
        val page = request.queryParam("page").orElse("1").toInt()
        val limit = request.queryParam("limit").orElse("10").toInt()
        val categoryIDParam = categoryClientIDParam?.toIntOrNull()
        return ServerResponse.ok()
            .body(
                clienteService.getCustomerByParams(nameClientParam, categoryIDParam, identificationClientParam, page, limit),
                Cliente::class.java
            )
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun verifyClientExistsByIdentification(request: ServerRequest): Mono<ServerResponse> {
        val identification = request.pathVariable("id")
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(clienteService.verifyClientExistsByIdentification(identification), String::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }
}
