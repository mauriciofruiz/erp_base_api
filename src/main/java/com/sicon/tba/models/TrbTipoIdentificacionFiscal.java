package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TRB_TIPOIDENTIFICACIONFISCAL")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TrbTipoIdentificacionFiscal {
    @Id
    @Column("ID_TIPOIDFISCAL")
    @JsonProperty("fiscalIdentificationTypeId")
    private String idTipoIdFiscal;

    @Column("TIPOTRANSACCION_TIPOIDFISCAL")
    @JsonProperty("fiscalIdentificationTypeTransaction")
    private String tipoTransaccionTipoIdFiscal;

    @Column("TIPOIDENTIFICACION_TIPOIDFISCAL")
    @JsonProperty("fiscalIdentificationTypeName")
    private String tipoIdentificacionTipoIdFiscal;
}
