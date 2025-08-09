package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_BLOQUEO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaBloqueo {
    @Id
    @Column("ID_BLOQUEO")
    @JsonProperty("blockId")
    private Integer idBloqueo;

    @Column("IP_BLOQUEO")
    @JsonProperty("blockIP")
    private String ipBloqueo;

    @Column("DESCRIPCION_BLOQUEO")
    @JsonProperty("blockDescription")
    private String descripcionBloqueo;

    @Column("ESTADO_BLOQUEO")
    @JsonProperty("blockState")
    private Boolean estadoBloqueo;
}
