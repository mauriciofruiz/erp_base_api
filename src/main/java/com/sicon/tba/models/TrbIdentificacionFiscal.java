package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TRB_IDENTIFICACIONFISCAL")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TrbIdentificacionFiscal {
    @Id
    @Column("ID_IDENTIFICACIONFISCAL")
    @JsonProperty("taxIdentificationId")
    private Integer idIdentificacionFiscal;

    @Column("COD_IDENTIFICACIONFISCAL")
    @JsonProperty("taxIdentificationCode")
    private String codIdentificacionFiscal;

    @Column("NOMBRE_IDENTIFICACIONFISCAL")
    @JsonProperty("taxIdentificationName")
    private String nombreIdentificacionFiscal;

    @Column("CODATS_IDENTIFICACIONFISCAL")
    @JsonProperty("atsIdentificationCode")
    private String codAtsIdentificacionFiscal;
}
