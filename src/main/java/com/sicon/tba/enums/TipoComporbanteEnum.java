package com.sicon.tba.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum TipoComporbanteEnum {
    FACTURA(1, "GENERADO", "01"),
    NOTA_VENTA(2, "CONFIRMADO", "02"),
    LIQUIDACION(3, "AUTORIZADO", "03"),
    NOTA_CREDITO(4, "ENVIADO", "04"),
    NOTA_DEBITO(5, "RECHAZADO", "05"),
    GUIAS_REMISION(6, "ANULADO", "06"),
    COMPROBANTE_RETENCION(7,"ANULADO","07");

    private Integer id;
    private String description;
    private String codigo;

    TipoComporbanteEnum(Integer id, String description, String codigo) {
        this.id = id;
        this.description = description;
        this.codigo = codigo;
    }

    public static String getCodigoById(Integer id) {
        for (TipoComporbanteEnum e : values()) {
            if (e.id.equals(id)) {
                return e.codigo;
            }
        }
        return null;
    }
}
