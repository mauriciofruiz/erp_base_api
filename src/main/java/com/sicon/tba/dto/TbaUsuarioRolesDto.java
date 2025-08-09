package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicon.tba.models.TbaRolUsuario;
import com.sicon.tba.models.TbaUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TbaUsuarioRolesDto {
    @JsonProperty("nick")
    private String nickUsr;

    @JsonProperty("branchId")
    private Integer idSucursal;

    @JsonProperty("personId")
    private Integer idPersona;

    @JsonProperty("position")
    private Integer cargoUsr;

    @JsonProperty("startTime")
    private String horaInicioUsr;

    @JsonProperty("endTime")
    private String horaFinUsr;

    @JsonProperty("stateUser")
    private boolean estado;

    @JsonProperty("photo")
    private String imgUsr;

    @JsonProperty("cashId")
    private Integer idCaja;

    @JsonProperty("rolesList")
    private List<TbaRolUsuario> detailRol;
}
