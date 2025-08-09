package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SesionDto {
    @JsonProperty("sessionId")
    private Integer idSesion;

    @JsonProperty("userId")
    private Integer idUsr;

    @JsonProperty("nick")
    private String nick_usr;

    @JsonProperty("personName")
    private String nombre_persona;

    @JsonProperty("state")
    private Boolean estado;
}
