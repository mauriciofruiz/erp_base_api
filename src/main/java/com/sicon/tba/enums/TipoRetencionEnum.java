package com.sicon.tba.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TipoRetencionEnum {

    IVA(1),
    FUENTE(2);

    private Integer id;

}
