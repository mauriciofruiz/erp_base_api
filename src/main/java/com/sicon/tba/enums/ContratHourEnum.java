package com.sicon.tba.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ContratHourEnum {

    FULL_TIME(BigDecimal.valueOf(8), "Tiempo Completo");

    private BigDecimal hours;
    private String name;


}
