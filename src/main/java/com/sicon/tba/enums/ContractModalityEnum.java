package com.sicon.tba.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ContractModalityEnum {

    INTERNSHIP("PASANTIA",0),
    UNDEFINED_TIME("INDEFINIDO - TIEMPO DE PRUEBA", 3),
    EVENTUAL("EVENTUAL", 0),
    BY_WORK("POR OBRA", 0),
    INDEFINIDO_JUVENIL("INDEFINIDO - JUVENIL", 0),
    CT_PROJECT("CT PROYECTO", 0),
    PRODUCTIVE_CONTRACT("CONTRATO PRODUCTIVO", 0),
    SPECIAL_EMERGENT("ESPECIAL EMERGENTE",0);


    private String nameModality;
    private Integer testTime;
}
