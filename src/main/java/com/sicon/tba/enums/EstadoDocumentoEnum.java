package com.sicon.tba.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum EstadoDocumentoEnum {
    GENERADO(1, "GENERADO"),
    CONFIRMADO(2, "CONFIRMADO"),
    AUTORIZADO(3, "AUTORIZADO"),
    ENVIADO(4, "ENVIADO"),
    RECHAZADO(5, "RECHAZADO"),
    ANULADO(6, "ANULADO");

    private Integer id;
    private String description;

    EstadoDocumentoEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static String getDescriptionById(Integer id) {
        for (EstadoDocumentoEnum e : values()) {
            if (e.id.equals(id)) {
                return e.description;
            }
        }
        return null;
    }
}
