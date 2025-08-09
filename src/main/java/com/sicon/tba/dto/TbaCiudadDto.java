package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaCiudadDto {

    @NotBlank(message = "El nombre de la provincia no puede estar vacío")
    @JsonProperty("provinceId")
    private String idProvincia;

    @NotBlank(message = "El nombre de la ciudad no puede estar vacío")
    @JsonProperty("cityName")
    private String nombreCiudad;

    @NotBlank(message = "El código de la ciudad no puede estar vacío")
    @JsonProperty("cityCode")
    private String codigoCiudad;

    @JsonProperty("provinceName")
    private String nombreProvincia;
}
