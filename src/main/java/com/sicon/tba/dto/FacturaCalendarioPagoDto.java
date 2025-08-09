package com.sicon.tba.dto;

import com.sicon.tba.models.AdqCalendarioPago;
import com.sicon.tba.models.AdqFacturaProveedor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FacturaCalendarioPagoDto
{
    private AdqFacturaProveedor adqFacturaProveedor;
    private AdqCalendarioPago adqCalendarioPago;

}
