package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbaParroquiaDto {

    @JsonProperty("parishId")
    private Integer idParroquia;

    @NotNull(message = "El Id de la ciudad no puede estar vacío")
    @JsonProperty("cityId")
    private Integer idCiudad;

    @NotNull(message = "El nombre de la parroquia no puede estar vacío")
    @JsonProperty("parishName")
    private String nombreParroquia;

    @NotNull(message = "El código de la parroquia no puede estar vacío")
    @JsonProperty("parishCode")
    private String codParroquia;
}
