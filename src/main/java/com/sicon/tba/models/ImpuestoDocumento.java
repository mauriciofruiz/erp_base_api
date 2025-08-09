package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("impuesto_documento")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class ImpuestoDocumento extends BaseEntity {
    @Id
    @Column("id_impuesto")
    @JsonProperty("taxId")
    private Integer idImpuesto;

    @Column("id_documento")
    @JsonProperty("documentId")
    private Long idDocumento;

    @Column("id_tipo_documento")
    @JsonProperty("documentTypeId")
    private Integer idTipoDocumento;

    @Column("id_voucher")
    @JsonProperty("voucherId")
    private Integer idVoucher;

    @Column("porcentaje_impuesto")
    @JsonProperty("taxPercentage")
    private BigDecimal porcentajeImpuesto;

    @Column("descripcion_impuesto")
    @JsonProperty("taxDescription")
    private String descripcionImpuesto;

    @Column("codigo_impuesto")
    @JsonProperty("taxCode")
    private String codigoImpuesto;

    @Column("id_cuenta")
    @JsonProperty("accountId")
    private Integer idCuenta;

    @Column("base_impuesto")
    @JsonProperty("taxBase")
    private BigDecimal baseImpuesto;

    @Column("monto_impuesto")
    @JsonProperty("taxAmount")
    private BigDecimal montoImpuesto;

    @Column("estado_impuesto")
    @JsonProperty("taxState")
    private Boolean estadoImpuesto;
}