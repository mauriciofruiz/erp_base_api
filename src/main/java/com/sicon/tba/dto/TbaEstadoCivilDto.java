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
public class TbaEstadoCivilDto {

    @NotNull(message = "El nombre del estado civil no puede estar vacío.")
    @JsonProperty("maritalStatusName")
    private String nombreEstadoCivil;

    @NotNull(message = "El código del estado civil no puede estar vacío.")
    @JsonProperty("maritalStatusCode")
    private String codigoEstadoCivil;
}
