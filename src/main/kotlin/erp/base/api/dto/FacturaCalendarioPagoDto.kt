package erp.base.api.dto

import erp.base.api.models.CalendarioPago
import erp.base.api.models.FacturaProveedor

data class FacturaCalendarioPagoDto(
    val adqFacturaProveedor: FacturaProveedor,
    val adqCalendarioPago: CalendarioPago,
)
