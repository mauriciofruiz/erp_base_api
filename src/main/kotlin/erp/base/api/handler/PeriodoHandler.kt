package erp.base.api.handler

import erp.base.api.exception.CustomException
import erp.base.api.models.Periodo
import erp.base.api.service.PeriodoService
import erp.base.api.utils.Utils
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.time.ZonedDateTime

@Component
class PeriodoHandler(
    private val periodoService: PeriodoService,
    private val resources: WebProperties.Resources,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun create(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(Periodo::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { periodo ->
                ServerResponse.ok()
                    .body(periodoService.createPeriodo(periodo), Periodo::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun getLastPeriods(serverRequest: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok()
            .body(periodoService.getLastPeriods(), Periodo::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun getRequestBySearchParams(request: ServerRequest): Mono<ServerResponse> {
        val moduleIdParam = request.queryParam("moduleId").orElse(null)
        val moduleId = if (moduleIdParam.isNullOrEmpty()) null else moduleIdParam.toIntOrNull()
        val startDate = request.queryParam("startDate").orElse(null)
        val startDateParam = startDate?.let { Utils.parseStringToZonedDateTime(it) }
        val endDate = request.queryParam("endDate").orElse(null)
        val endDateParam = endDate?.let { Utils.parseStringToZonedDateTime(it) }
        val page = request.queryParam("page").orElse("1").toInt()
        val limit = request.queryParam("limit").orElse("10").toInt()
        val sortDirection = request.queryParam("sortDirection").orElse(null)
        val sortField = request.queryParam("sortField").orElse(null)

        if (request.queryParams().isEmpty()) {
            return getLastPeriods(request)
        }
        return ServerResponse.ok()
            .body(
                periodoService.getRequestBySearchParams(
                    moduleId,
                    startDateParam,
                    endDateParam,
                    page,
                    limit,
                    sortDirection,
                    sortField,
                ),
                Periodo::class.java,
            )
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }
}
