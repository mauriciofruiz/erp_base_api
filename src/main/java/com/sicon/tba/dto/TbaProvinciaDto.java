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
public class TbaProvinciaDto {

    @NotBlank(message = "El id de la provincia no puede estar vacío")
    @JsonProperty("provinceId")
    private String idProvincia;

    @NotNull(message = "El id del país no puede estar vacío")
    @JsonProperty("countryId")
    private Integer idPais;

    @NotBlank(message = "El nombre de la provincia no puede estar vacío")
    @JsonProperty("provinceName")
    private String nombreProvincia;

    @NotNull(message = "El código de la provincia no puede estar vacío")
    @JsonProperty("regionCode")
    private Integer codRegion;
}
