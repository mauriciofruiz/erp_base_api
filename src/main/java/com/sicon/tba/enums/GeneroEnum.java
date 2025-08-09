package com.sicon.tba.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum GeneroEnum {
    MASCULINO(1, "MASCULINO", "M"),
    FEMENINO(2, "FEMENINO", "F"),
    NO_APLICABLE(3, "NO APLICABLE","0");

    private Integer id;
    private String description;
    private String codigo;

    GeneroEnum(Integer id, String description, String codigo) {
        this.id = id;
        this.description = description;
        this.codigo = codigo;
    }

    public static String getDescriptionById(Integer id) {
        for (GeneroEnum e : values()) {
            if (e.id.equals(id)) {
                return e.description;
            }
        }
        return null;
    }
}
