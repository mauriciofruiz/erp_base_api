package com.sicon.tba.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum CategoriaClienteEnum {
    NORMAL(1, "NORMAL"),
    PREFERENCIAL(2, "PREFERENCIAL"),
    CONSTRUCTOR(3, "CONSTRUCTOR"),
    DISTRIBUIDOR(5, "DISTRIBUIDOR"),
    ACCIONISTA(6, "ACCIONISTA"),
    DISTRIBUIDOR_FERRETERO(7, "DISTRIBUIDOR FERRETERO"),
    VENTA_DISTRIBUIDORES(8, "VENTA DISTRIBUIDORES"),
    DISTRIBUIDOR_STDO_LZ(9, "DISTRIBUIDOR STDO LZ"),
    DSCTO_DISTRIBUIDOR_FF(10, "DSCTO DISTRIBUIDOR FF"),
    DISTRIBUIDOR_CORPORATIVO(11, "DISTRIBUIDOR CORPORATIVO");

    private Integer id;
    private String description;

    CategoriaClienteEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static String getDescriptionById(Integer id) {
        for (CategoriaClienteEnum e : values()) {
            if (e.id.equals(id)) {
                return e.description;
            }
        }
        return null;
    }
}
