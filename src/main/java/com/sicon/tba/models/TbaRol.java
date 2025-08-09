package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Table("TBA_ROL")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaRol extends BaseEntity {
    @Id
    @Column("ID_ROL")
    @JsonProperty("roleId")
    private Integer idRol;

    @Column("NOMBRE_ROL")
    @JsonProperty("roleName")
    private String nombreRol;

    @Column("DESCRIPCION_ROL")
    @JsonProperty("roleDescription")
    private String descripcionRol;

    @Column("HORAINICIO_ROL")
    @JsonProperty("startTime")
    private String horaInicioRol;

    @Column("HORAFIN_ROL")
    @JsonProperty("endTime")
    private String horaFinRol;

    @Column("TIEMPO_ROL")
    @JsonProperty("roleTime")
    private Integer tiempoRol;
}
