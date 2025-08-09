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

@Table("VTS_COBRO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VtsCobro {
    @Id
    @Column("ID_COBRO")
    @JsonProperty("paymentId")
    private Integer idCobro;

    @Column("ID_USR")
    @JsonProperty("userId")
    private Integer idUsuario;

    @Column("ID_ASIENTO")
    @JsonProperty("accountingEntryId")
    private Integer idAsiento;

    @Column("ID_CAJA")
    @JsonProperty("cashboxId")
    private Integer idCaja;

    @Column("ID_CLIENTE")
    @JsonProperty("clientId")
    private Integer idCliente;

    @Column("ID_APERTURACAJA")
    @JsonProperty("cashOpeningId")
    private Integer idAperturaCaja;

    @Column("MONTO_COBRO")
    @JsonProperty("amount")
    private BigDecimal montoCobro;

    @Column("FECHA_COBRO")
    @JsonProperty("paymentDate")
    private LocalDateTime fechaCobro;

    @Column("FORMAPAGO_COBRO")
    @JsonProperty("paymentMethod")
    private String formaPagoCobro;

    @Column("ANTICIPO_COBRO")
    @JsonProperty("advancePayment")
    private Boolean anticipoCobro;

    @Column("ANULADO_COBRO")
    @JsonProperty("cancelled")
    private Boolean anuladoCobro;

    @Column("ANTICIPOTRANSPORTE_COBRO")
    @JsonProperty("transportAdvancePayment")
    private Boolean anticipotransporteCobro;

    @Column("CODNC_COBRO")
    @JsonProperty("ncCode")
    private String codNcCobro;

    @Column("ESRECUPERACION_COBRO")
    @JsonProperty("recoveryPayment")
    private Boolean esRecuperacionCobro;

    @Column("MOTIVOANULACION_COBRO")
    @JsonProperty("cancellationReason")
    private String motivoAnulacionCobro;

    @Column("FECHAEDICIONLOTE_COBRO")
    @JsonProperty("batchEditionDate")
    private LocalDateTime fechaEdicionLoteCobro;

    @Column("USUARIOEDITALOTE_COBRO")
    @JsonProperty("batchEditionUser")
    private String usuarioEdicionLoteCobro;
}
