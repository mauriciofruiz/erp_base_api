package com.sicon.tba.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TaxIdentificationEnum {

    RUC(1, "RUC"),
    CEDULA(2, "CÉDULA"),
    PASAPORTE(3, "PASAPORTE"),
    IDENTIFICATION_EXT(4, "IDENTIFICACION EXT"),
    CONSUMIDOR_FINAL(5, "CONSUMIDOR FINAL");

    private Integer id;
    private String name;

    //Obtiene la descripción por Id
    public static String getNameById(Integer id) {
        for (TaxIdentificationEnum e : values()) {
            if (e.id.equals(id)) {
                return e.name;
            }
        }
        return null;
    }

    //Obtiene el Id por la descripción
    public static Integer getIdByName(String name) {
        for (TaxIdentificationEnum e : values()) {
            if (e.name.equals(name)) {
                return e.id;
            }
        }
        return null;
    }
}
