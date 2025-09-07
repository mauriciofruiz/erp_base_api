package erp.base.api.dto;

import erp.base.api.models.AdqCalendarioPago;
import erp.base.api.models.AdqFacturaProveedor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FacturaCalendarioPagoDto
{
    private AdqFacturaProveedor adqFacturaProveedor;
    private AdqCalendarioPago adqCalendarioPago;

}
