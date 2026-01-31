package erp.base.api.handler

import erp.base.api.constants.Constants
import erp.base.api.dto.BancoDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Banco
import erp.base.api.service.BancoService
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class BancoHandler(
    private val bancoService: BancoService,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun createBanck(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(BancoDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(bancoService.createBank(dto), Banco::class.java) }
            .onErrorMap { e -> log.error(e.message); CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "") }

    fun update(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return request.bodyToMono(BancoDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(bancoService.updateBank(id, dto).onErrorMap { e -> log.error(e.message); CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "") }, Banco::class.java)
            }
    }

    fun getById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(bancoService.getById(id), Banco::class.java)
            .onErrorMap { e -> log.error(e.message); CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "") }
    }

    fun getBanksBySearchParams(request: ServerRequest): Mono<ServerResponse> {
        val nameBank = request.queryParam("bankName").orElse(null)
        val numBank = request.queryParam("bankAccountNumber").orElse(null)
        val page = request.queryParam("page").orElse("1").toInt()
        val limit = request.queryParam("limit").orElse("10").toInt()
        val sortDirection = request.queryParam("sortDirection").orElse(null)
        val sortField = request.queryParam("sortField").orElse(null)
        return ServerResponse.ok().body(bancoService.getRequestBySearchParams(nameBank, numBank, page, limit, sortDirection, sortField), Banco::class.java)
            .onErrorMap { e -> log.error(Constants.GENERIC_ERROR_MESSAGE_WITH_CAUSE, e.cause?.message ?: e.message, e); CustomException(HttpStatus.BAD_REQUEST, Constants.GENERIC_ERROR_MESSAGE) }
    }

    fun getBankList(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(bancoService.getBankList(), Banco::class.java)
            .onErrorMap { e -> log.error(Constants.GENERIC_ERROR_MESSAGE_WITH_CAUSE, e.cause?.message ?: e.message, e); CustomException(HttpStatus.BAD_REQUEST, Constants.GENERIC_ERROR_MESSAGE) }

    fun getBankListByIdSucursal(request: ServerRequest): Mono<ServerResponse> {
        val idSucursal = request.queryParam("branchId").orElse(null)!!.toInt()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(bancoService.getBankListByIdSucursal(idSucursal), Banco::class.java)
            .onErrorMap { e -> log.error(Constants.GENERIC_ERROR_MESSAGE_WITH_CAUSE, e.cause?.message ?: e.message, e); CustomException(HttpStatus.BAD_REQUEST, Constants.GENERIC_ERROR_MESSAGE) }
    }

    fun getBankListIsBank(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(bancoService.getBankListIsBank(), Banco::class.java)
            .onErrorMap { e -> log.error(e.message); CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "") }

    fun getLastCheckBank(request: ServerRequest): Mono<ServerResponse> {
        val bankId = request.queryParam("bankId").orElse("1").toInt()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(bancoService.getLastCheckBank(bankId), Int::class.javaObjectType)
            .onErrorMap { e -> log.error(e.message); CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "") }
    }
}
