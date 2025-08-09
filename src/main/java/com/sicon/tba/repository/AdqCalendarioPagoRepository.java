package com.sicon.tba.repository;

import com.sicon.tba.models.AdqCalendarioPago;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface AdqCalendarioPagoRepository extends ReactiveCrudRepository<AdqCalendarioPago, Integer>{

    @Query("SELECT * " +
            "FROM ADQ_CALENDARIOPAGO CALENDARIO "+
            "INNER JOIN ADQ_FACTURAPROVEEDOR FACTURA ON CALENDARIO.ID_FACTURAPROVEEDOR = FACTURA.ID_FACTURAPROVEEDOR "+
            "WHERE FACTURA.ID_FACTURAPROVEEDOR = :providerId "+
            "AND FACTURA.ID_ESTADOFACPROV NOT IN(:idEstadoFacturaProveedorList)")
    Flux<AdqCalendarioPago> findPaymentScheduleByProviderId(Integer providerId, List<Integer> idEstadoFacturaProveedorList);
}
