package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TRB_ORIGENINGRESO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TrbOrigenIngreso {
    @Id
    @Column("ID_ORIGENINGRESO")
    @JsonProperty("originId")
    private Integer idOrigenIngreso;

    @Column("NOMBRE_ORIGENINGRESO")
    @JsonProperty("originName")
    private String nombreOrigenIngreso;

    @Column("COD_ORIGENINGRESO")
    @JsonProperty("originCode")
    private String codOrigenIngreso;
}
