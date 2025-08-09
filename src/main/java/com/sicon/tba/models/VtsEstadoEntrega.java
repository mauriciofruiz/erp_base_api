package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("VTS_ESTADOENTREGA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VtsEstadoEntrega {
    @Id
    @Column("ID_ESTADOENTREGA")
    @JsonProperty("deliveryStatusId")
    private Integer idEstadoEntrega;

    @Column("NOMBRE_ESTADOENTREGA")
    @JsonProperty("deliveryStatusName")
    private String nombreEstadoEntrega;
}
