package com.sicon.tba.repository;

import com.sicon.tba.models.Periodo;
import com.sicon.tba.repository.custom.PeriodoRepositoryCustom;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface PeriodoRepository extends ReactiveCrudRepository<Periodo, Integer>, PeriodoRepositoryCustom {
    @Query("SELECT DISTINCT ON (id_modulo) *" +
            " FROM con_periodo" +
            " ORDER BY id_modulo, fechacierre_periodo DESC;")
    Flux<Periodo> getLastPeriods();
}
