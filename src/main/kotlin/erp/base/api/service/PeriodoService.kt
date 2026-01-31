package erp.base.api.service

import erp.base.api.exception.CustomException
import erp.base.api.models.Periodo
import erp.base.api.repository.PeriodoRepository
import erp.base.api.utils.Paginacion
import erp.base.api.utils.Utils
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.ZonedDateTime

@Service
class PeriodoService(private val periodoRepository: PeriodoRepository) {

    fun createPeriodo(periodo: Periodo): Mono<Periodo> {
        val newPeriodo = Periodo(
            idPeriodo = null,
            fechaCierrePeriodo = periodo.fechaCierrePeriodo,
            fechaAnteriorPeriodo = periodo.fechaAnteriorPeriodo,
            idModulo = periodo.idModulo,
            idUsuarioCreacion = null,
            idUsuarioModificacion = null,
            fechaCreacion = null,
            fechaModificacion = null
        )
        return periodoRepository.save(newPeriodo)
    }

    fun getLastPeriods(): Flux<Periodo> =
        periodoRepository.getLastPeriods()

    fun getRequestBySearchParams(
        moduleId: Int?,
        startDateParam: ZonedDateTime?,
        endDateParam: ZonedDateTime?,
        page: Int,
        limit: Int,
        sortDirection: String?,
        sortfield: String?
    ): Mono<Paginacion<Periodo>> {
        val endDate = Utils.getEndDateForPagination(false, endDateParam)
        val startDate = Utils.getStartDateForPagination(false, startDateParam)
        val periodoFlux = periodoRepository.getBySearchParams(moduleId, startDate, endDate, sortfield, sortDirection, limit, (page - 1) * limit)
        val total = periodoRepository.getCountBySearchParams(moduleId, startDate, endDate)
        return total.flatMap { totalCount ->
            periodoFlux.collectList().map { cajaList -> Paginacion(cajaList, page, limit, totalCount.toInt()) }
        }
    }
}
