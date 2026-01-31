package erp.base.api.repository

import erp.base.api.models.Periodo
import erp.base.api.repository.custom.PeriodoRepositoryCustom
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface PeriodoRepository : ReactiveCrudRepository<Periodo, Int>, PeriodoRepositoryCustom {

    @Query("SELECT DISTINCT ON (id_modulo) * FROM con_periodo ORDER BY id_modulo, fechacierre_periodo DESC;")
    fun getLastPeriods(): Flux<Periodo>
}
