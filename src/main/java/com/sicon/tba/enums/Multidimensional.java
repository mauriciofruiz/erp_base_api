package com.sicon.tba.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Multidimensional {

    CUSTOMER(1, "cliente"),
    EMPLOYEE(2, "empleado"),
    IMPORT(3, "importacion"),
    VENDOR(4, "proveedor"),
    PROJECT(5, "proyecto"),
    BRANCH(6, "sucursal");


    private Integer identification;
    private String name;
}
