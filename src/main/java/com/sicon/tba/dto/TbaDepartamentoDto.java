package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TbaDepartamentoDto {

    @JsonProperty("departmentId")
    private Integer idDepartamento;

    @NotBlank(message = "El nombre del departamento no puede estar vacío")
    @JsonProperty("departmentName")
    private String nombreDepartamento;

    @NotNull(message = "El estado del departamento no puede estar vacío")
    @JsonProperty("departmentStatus")
    private Boolean estadoDepartamento;

    @NotNull(message = "El id del grupo de área no puede estar vacío")
    @JsonProperty("areaGroupId")
    private Integer idAreaGrupo;

    @JsonProperty("positionList")
    private List<TbaCargoDto> tbaCargoDtoList;
}
