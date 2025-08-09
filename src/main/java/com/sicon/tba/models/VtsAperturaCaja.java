package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table("VTS_APERTURACAJA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VtsAperturaCaja {
    @Id
    @Column("ID_APERTURACAJA")
    @JsonProperty("cashOpeningId")
    private Integer idAperturaCaja;

    @Column("ID_CAJA")
    @JsonProperty("cashboxId")
    private Integer idCaja;

    @Column("ID_USR")
    @JsonProperty("userId")
    private Integer idUsuario;

    @Column("ID_ASIENTO")
    @JsonProperty("accountingEntryId")
    private Integer idAsiento;

    @Column("VALORINICIAL_APERTURACAJA")
    @JsonProperty("initialValue")
    private BigDecimal valorInicial;

    @Column("VALORFINAL_APERTURACAJA")
    @JsonProperty("finalValue")
    private BigDecimal valorFinal;

    @Column("FECHAAPERTURA_APERTURACAJA")
    @JsonProperty("openingDate")
    private LocalDateTime fechaApertura;

    @Column("FECHACIERRE_APERTURACAJA")
    @JsonProperty("closingDate")
    private LocalDateTime fechaCierre;

    @Column("ESTADO_APERTURACAJA")
    @JsonProperty("state")
    private Boolean estado;

    @Column("ESMANUAL_APERTURACAJA")
    @JsonProperty("manual")
    private Boolean esManual;
}
