package com.sicon.tba.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum OrigenIngresoEnum {

    EMPLEADO_PUBLICO(1, "EMPLEADO PÚBLICO"),
    EMPLEADO_PRIVADO(2, "EMPLEADO PRIVADO"),
    INDEPENDIENTE(3, "INDEPENDIENTE"),
    AMA_DE_CASA_O_ESTUDIANTE(4, "AMA DE CASA O ESTUDIANTE"),
    RENTISTA(5, "RENTISTA"),
    JUBILADO(6, "JUBILADO"),
    REMESAS_DEL_EXTERIOR(7, "REMESAS DEL EXTERIOR");

    private Integer id;
    private String description;

    OrigenIngresoEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

}
