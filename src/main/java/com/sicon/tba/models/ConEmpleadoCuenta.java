package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("CON_EMPLEADOCUENTA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ConEmpleadoCuenta {
    @Id
    @Column("ID_EMPLEADOCUENTA")
    @JsonProperty("employeeCountId")
    private Integer idEmpleadoCuenta;

    @Column("ID_EMPLEADO")
    @JsonProperty("employeeId")
    private Integer idEmpleado;

    @Column("ID_CUENTA")
    @JsonProperty("countId")
    private Integer idCuenta;
}
