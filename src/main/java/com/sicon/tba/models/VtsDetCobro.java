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
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table("VTS_DETCOBRO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VtsDetCobro {
    @Id
    @Column("ID_DETCOBRO")
    @JsonProperty("paymentDetailId")
    private Integer idDetCobro;

    @Column("ID_FACTURA")
    @JsonProperty("invoiceId")
    private Integer idFactura;

    @Column("ID_TARJETA")
    @JsonProperty("cardId")
    private Integer idTarjeta;

    @Column("ID_ADQUIRIENTE")
    @JsonProperty("acquirerId")
    private Integer idAdquiriente;

    @Column("ID_BANCOCATALOGO")
    @JsonProperty("bankCatalogId")
    private Integer idBancoCatalogo;

    @Column("ID_FORMAPAGOCOBRO")
    @JsonProperty("paymentMethodId")
    private String idFormaPagoCobro;

    @Column("ID_COBRO")
    @JsonProperty("paymentId")
    private Integer idCobro;

    @Column("VALOR_DETCOBRO")
    @JsonProperty("paymentAmount")
    private BigDecimal valorDetCobro;

    @Column("NUMERODOCUMENTO_DETCOBRO")
    @JsonProperty("documentNumber")
    private String numeroDocumentoDetCobro;

    @Column("FECHATRANSACCION_DETCOBRO")
    @JsonProperty("transactionDate")
    private LocalDateTime fechaTransaccionDetCobro;

    @Column("FECHACOBRO_DETCOBRO")
    @JsonProperty("paymentDate")
    private LocalDate fechaCobroDetCobro;

    @Column("NUMEROTC_DETCOBRO")
    @JsonProperty("cardNumber")
    private String numeroTcDetCobro;

    @Column("LOTETARJETA_DETCOBRO")
    @JsonProperty("cardBatch")
    private String loteTarjetaDetCobro;

    @Column("MESESDIFERIDO_DETCOBRO")
    @JsonProperty("installmentMonths")
    private Integer mesesDiferidoDetCobro;

    @Column("NUMCUOTA_DETCOBRO")
    @JsonProperty("installmentNumber")
    private Integer numeroCuotaDetCobro;

    @Column("OBS_DETCOBRO")
    @JsonProperty("observation")
    private String observacionDetCobro;

    @Column("VALORANTICIPO_DETCOBRO")
    @JsonProperty("advanceAmount")
    private BigDecimal valorAnticipoDetCobro;

    @Column("VALORTRANS_DETCOBRO")
    @JsonProperty("transactionAmount")
    private BigDecimal valorTransDetCobro;

    @Column("CODNC_DETCOBRO")
    @JsonProperty("ncCode")
    private BigDecimal codNcDetCobro;

    @Column("ESTADO_DETCOBRO")
    @JsonProperty("status")
    private Boolean estadoDetCobro;

    @Column("ID_FACTURACUOTA")
    @JsonProperty("installmentInvoiceId")
    private Integer idFacturaCuota;
}
