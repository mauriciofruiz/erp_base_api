package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BitacoraDto {

    @JsonProperty("logId")
    private Integer id;

    @JsonProperty("userId")
    private Integer idUsuario;

    @JsonProperty("userName")
    private String nombreUsuario;

    @JsonProperty("identifier")
    private Integer identificador;

    @JsonProperty("table")
    private String tabla;

    @JsonProperty("field")
    private String campo;

    @JsonProperty("oldValue")
    private String oldValue;

    @JsonProperty("newValue")
    private String newValue;

    @JsonProperty("date")
    private ZonedDateTime fecha;

    @JsonProperty("deviceIp")
    private String ip;

    @JsonProperty("deviceName")
    private String dispositivo;
}
