package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbaGeneroDto {

    @NotNull(message = "El nombre del género no puede estar vacío.")
    @JsonProperty("genderName")
    private String nombreGenero;

    @NotNull(message = "El código del género no puede estar vacío.")
    @JsonProperty("genderCode")
    private String codigoGenero;
}
