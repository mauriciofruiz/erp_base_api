package com.sicon.tba.service;

import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.Caja;
import com.sicon.tba.models.Periodo;
import com.sicon.tba.models.Rol;
import com.sicon.tba.repository.PeriodoRepository;
import com.sicon.tba.repository.RolRepository;
import com.sicon.tba.utils.Paginacion;
import com.sicon.tba.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class PeriodoService {

    private final PeriodoRepository periodoRepository;

    public Mono<Periodo> createPeriodo(Periodo periodo) {
        Periodo newPeriodo = Periodo.builder()
                .fechaCierrePeriodo(periodo.getFechaCierrePeriodo())
                .fechaAnteriorPeriodo(periodo.getFechaAnteriorPeriodo())
                .idModulo(periodo.getIdModulo())
                .build();
        return periodoRepository.save(newPeriodo);
    }

    public Flux<Periodo> getLastPeriods() {
        return periodoRepository.getLastPeriods();
    }

    public Mono<Paginacion<Periodo>> getRequestBySearchParams(
            Integer moduleId,
            ZonedDateTime startDateParam,
            ZonedDateTime endDateParam,
            Integer page,
            Integer limit,
            String sortDirection,
            String sortfield
    ) {
        ZonedDateTime endDate = Utils.getEndDateForPagination(false, endDateParam);
        ZonedDateTime startDate = Utils.getStartDateForPagination(false, startDateParam);
        Flux<Periodo> periodoFlux = periodoRepository.getBySearchParams(moduleId, startDate, endDate, sortfield, sortDirection, limit, (page - 1) * limit);
        Mono<Long> total = periodoRepository.getCountBySearchParams(moduleId, startDate, endDate);
        return total.flatMap(totalCount -> periodoFlux.collectList().map(cajaList -> new Paginacion<>(cajaList, page, limit, totalCount.intValue())));
    }
}
