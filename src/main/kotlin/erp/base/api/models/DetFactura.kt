package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal

@Table("DETFACTURA")
data class DetFactura(
    @Id
    @Column("ID_DETFACTURA")
    @JsonProperty("invoiceDetailId")
    val idDetFactura: Int,
    @Column("ID_PRODUCTO")
    @JsonProperty("productId")
    val idProducto: Int? = null,
    @Column("ID_FACTURA")
    @JsonProperty("invoiceId")
    val idFactura: Int? = null,
    @Column("CANTIDAD_DETFACTURA")
    @JsonProperty("quantity")
    val cantidadDetFactura: BigDecimal? = null,
    @Column("PRECIO_DETFACTURA")
    @JsonProperty("price")
    val precioDetFactura: BigDecimal? = null,
    @Column("PORCDESCUENTO_DETFACTURA")
    @JsonProperty("discountPercentage")
    val porcentajeDescuentoDetFactura: BigDecimal? = null,
    @Column("DESCUENTO_DETFACTURA")
    @JsonProperty("discountAmount")
    val descuentoDetFactura: BigDecimal? = null,
    @Column("PORCPROMOCION_DETFACTURA")
    @JsonProperty("promotionPercentage")
    val porcentajePromocionDetFactura: BigDecimal? = null,
    @Column("DESCPROMOCION_DETFACTURA")
    @JsonProperty("promotionAmount")
    val descuentoPromocionDetFactura: BigDecimal? = null,
    @Column("PRECIOCLIENTE_DETFACTURA")
    @JsonProperty("customerPrice")
    val precioClienteDetFactura: BigDecimal? = null,
    @Column("IVA_DETFACTURA")
    @JsonProperty("isVat")
    val ivaDetFactura: Boolean? = null,
    @Column("TOTAL_DETFACTURA")
    @JsonProperty("totalAmount")
    val totalDetFactura: BigDecimal? = null,
    @Column("ESPROMO_DETFACTURA")
    @JsonProperty("isPromotion")
    val esPromoDetFactura: Boolean? = null,
    @Column("CANTDEVUELTA_DETFACTURA")
    @JsonProperty("returnedQuantity")
    val cantidadDevueltaDetFactura: BigDecimal? = null,
    @Column("CANTDESPACHADA_DETFACTURA")
    @JsonProperty("shippedQuantity")
    val cantidadDespachadaDetFactura: BigDecimal? = null,
    @Column("CANTXDESPACHAR_DETFACTURA")
    @JsonProperty("quantityToShip")
    val cantidadPorDespacharDetFactura: BigDecimal? = null,
    @Column("CANTRECEPCIONADA_DETFACTURA")
    @JsonProperty("receivedQuantity")
    val cantidadRecepcionadaDetFactura: BigDecimal? = null,
    @Column("OBS_DETFACTURA")
    @JsonProperty("observation")
    val observacionDetFactura: String? = null,
    @Column("CODAF_DETFACTURA")
    @JsonProperty("afCode")
    val codAfDetFactura: Int? = null,
    @Column("ESTADO_DETFACTURA")
    @JsonProperty("status")
    val estadoDetFactura: Boolean? = null,
    @Column("DESCPROMESPECIAL_DETFACTURA")
    @JsonProperty("specialPromotionAmount")
    val descuentoPromocionEspecialDetFactura: BigDecimal? = null,
    @Column("PORCDESCPROMESPECIAL_DETFACTURA")
    @JsonProperty("specialPromotionPercentage")
    val porcentajeDescuentoPromocionEspecialDetFactura: BigDecimal? = null,
)
