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

@Table("VTS_DETFACTURA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VtsDetFactura {
    @Id
    @Column("ID_DETFACTURA")
    @JsonProperty("invoiceDetailId")
    private Integer idDetFactura;

    @Column("ID_PRODUCTO")
    @JsonProperty("productId")
    private Integer idProducto;

    @Column("ID_FACTURA")
    @JsonProperty("invoiceId")
    private Integer idFactura;

    @Column("CANTIDAD_DETFACTURA")
    @JsonProperty("quantity")
    private BigDecimal cantidadDetFactura;

    @Column("PRECIO_DETFACTURA")
    @JsonProperty("price")
    private BigDecimal precioDetFactura;

    @Column("PORCDESCUENTO_DETFACTURA")
    @JsonProperty("discountPercentage")
    private BigDecimal porcentajeDescuentoDetFactura;

    @Column("DESCUENTO_DETFACTURA")
    @JsonProperty("discountAmount")
    private BigDecimal descuentoDetFactura;

    @Column("PORCPROMOCION_DETFACTURA")
    @JsonProperty("promotionPercentage")
    private BigDecimal porcentajePromocionDetFactura;

    @Column("DESCPROMOCION_DETFACTURA")
    @JsonProperty("promotionAmount")
    private BigDecimal descuentoPromocionDetFactura;

    @Column("PRECIOCLIENTE_DETFACTURA")
    @JsonProperty("customerPrice")
    private BigDecimal precioClienteDetFactura;

    @Column("IVA_DETFACTURA")
    @JsonProperty("isVat")
    private Boolean ivaDetFactura;

    @Column("TOTAL_DETFACTURA")
    @JsonProperty("totalAmount")
    private BigDecimal totalDetFactura;

    @Column("ESPROMO_DETFACTURA")
    @JsonProperty("isPromotion")
    private Boolean esPromoDetFactura;

    @Column("CANTDEVUELTA_DETFACTURA")
    @JsonProperty("returnedQuantity")
    private BigDecimal cantidadDevueltaDetFactura;

    @Column("CANTDESPACHADA_DETFACTURA")
    @JsonProperty("shippedQuantity")
    private BigDecimal cantidadDespachadaDetFactura;

    @Column("CANTXDESPACHAR_DETFACTURA")
    @JsonProperty("quantityToShip")
    private BigDecimal cantidadPorDespacharDetFactura;

    @Column("CANTRECEPCIONADA_DETFACTURA")
    @JsonProperty("receivedQuantity")
    private BigDecimal cantidadRecepcionadaDetFactura;

    @Column("OBS_DETFACTURA")
    @JsonProperty("observation")
    private String observacionDetFactura;

    @Column("CODAF_DETFACTURA")
    @JsonProperty("afCode")
    private Integer codAfDetFactura;

    @Column("ESTADO_DETFACTURA")
    @JsonProperty("status")
    private Boolean estadoDetFactura;

    @Column("DESCPROMESPECIAL_DETFACTURA")
    @JsonProperty("specialPromotionAmount")
    private BigDecimal descuentoPromocionEspecialDetFactura;

    @Column("PORCDESCPROMESPECIAL_DETFACTURA")
    @JsonProperty("specialPromotionPercentage")
    private BigDecimal porcentajeDescuentoPromocionEspecialDetFactura;
}
