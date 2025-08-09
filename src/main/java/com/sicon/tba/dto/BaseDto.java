package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseDto {

    @JsonProperty("creationUserId")
    private Integer idUsuarioCreacion;

    @JsonProperty("modificationUserId")
    private Integer idUsuarioModificacion;

    @JsonProperty("registrationDate")
    private LocalDateTime fechaCreacion;

    @JsonProperty("modificationDate")
    private LocalDateTime fechaModificacion;
}