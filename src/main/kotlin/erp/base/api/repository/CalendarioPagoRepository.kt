package erp.base.api.repository

import erp.base.api.models.CalendarioPago
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface CalendarioPagoRepository : ReactiveCrudRepository<CalendarioPago, Int> {

    @Query(
        "SELECT * FROM CALENDARIOPAGO CALENDARIO INNER JOIN FACTURAPROVEEDOR FACTURA ON CALENDARIO.ID_FACTURAPROVEEDOR = FACTURA.ID_FACTURAPROVEEDOR " +
            "WHERE FACTURA.ID_FACTURAPROVEEDOR = :providerId AND FACTURA.ID_ESTADOFACPROV NOT IN(:idEstadoFacturaProveedorList)"
    )
    fun findPaymentScheduleByProviderId(providerId: Int, idEstadoFacturaProveedorList: List<Int>): Flux<CalendarioPago>
}
