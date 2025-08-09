package com.sicon.tba.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum AreaGroupEnum {

    ADMINISTRATION(1, "Admin"),
    SALES(2, "Ventas"),
    PRODUCTION(3, "Produccion");

    private Integer identification;
    private String name;

}
