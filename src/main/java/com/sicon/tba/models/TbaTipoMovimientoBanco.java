package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_TIPOMOVIMIENTOBANCO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaTipoMovimientoBanco {
    @Id
    @Column("ID_TIPOMOVIMIENTOBANCO")
    @JsonProperty("bankMovementTypeId")
    private Integer idTipoMovimientoBanco;

    @Column("NOMBRE_TIPOMOVIMIENTOBANCO")
    @JsonProperty("bankMovementTypeName")
    private String nombreTipoMovimientoBanco;

    @Column("ESTADO_TIPOMOVIMIENTOBANCO")
    @JsonProperty("bankMovementTypeState")
    private Boolean estadoTipoMovimientoBanco;
}
