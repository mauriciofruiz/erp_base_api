package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RrhContactoEmergenciaDto {

    @JsonProperty("contactEmergencyId")
    private Integer idContactoemergencia;

    @JsonProperty("kinId")
    private Integer idParentesco;

    @JsonProperty("nameContactEmergency")
    private String nombreContactoemergencia;

    @JsonProperty("name")
    private String nombreParentesco;

    @JsonProperty("cellphoneEmergencyContact")
    private String celularContactoemergencia;

    @JsonProperty("telephoneEmergencyContact")
    private String telefonoContactoemergencia;
}
