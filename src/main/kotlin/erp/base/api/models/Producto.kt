package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table("PRODUCTO")
data class Producto(
    @Id
    @Column("ID_PRODUCTO")
    @JsonProperty("productId")
    val idProducto: Int,
    @Column("ID_ROTACIONPRODUCTO")
    @JsonProperty("rotationProductId")
    val idRotacionProducto: Int? = null,
    @Column("ID_SUBGRUPO")
    @JsonProperty("subgroupId")
    val idSubgrupo: Int? = null,
    @Column("ID_TARIFAIVA")
    @JsonProperty("tarifaIvaId")
    val idTarifaIva: Int? = null,
    @Column("ID_UNIDADMEDIDA")
    @JsonProperty("unidadMedidaId")
    val idUnidadMedida: Int? = null,
    @Column("COD_PRODUCTO")
    @JsonProperty("productCode")
    val codProducto: String? = null,
    @Column("NOMBRECORTO_PRODUCTO")
    @JsonProperty("shortProductName")
    val nombreCortoProducto: String? = null,
    @Column("NOMBRELARGO_PRODUCTO")
    @JsonProperty("longProductName")
    val nombreLargoProducto: String? = null,
    @Column("COSTOUNITARIO_PRODUCTO")
    @JsonProperty("unitCost")
    val costoUnitarioProducto: BigDecimal? = null,
    @Column("PVPUNITARIO_PRODUCTO")
    @JsonProperty("unitPrice")
    val pvpUnitarioProducto: BigDecimal? = null,
    @Column("MARCAIVA_PRODUCTO")
    @JsonProperty("isVATApplicable")
    val marcaIvaProducto: Boolean? = null,
    @Column("CARACTERISTICA_PRODUCTO")
    @JsonProperty("productCharacteristics")
    val caracteristicaProducto: String? = null,
    @Column("PESOKG_PRODUCTO")
    @JsonProperty("productWeight")
    val pesoKgProducto: BigDecimal? = null,
    @Column("LARGO_PRODUCTO")
    @JsonProperty("productLength")
    val largoProducto: BigDecimal? = null,
    @Column("ANCHO_PRODUCTO")
    @JsonProperty("productWidth")
    val anchoProducto: BigDecimal? = null,
    @Column("ESPESOR_PRODUCTO")
    @JsonProperty("productThickness")
    val espesorProducto: BigDecimal? = null,
    @Column("MARCA_PRODUCTO")
    @JsonProperty("productBrand")
    val marcaProducto: String? = null,
    @Column("PRECIOFACTURA_PRODUCTO")
    @JsonProperty("invoicePrice")
    val precioFacturaProducto: BigDecimal? = null,
    @Column("ESMATERIAPRIMA_PRODUCTO")
    @JsonProperty("isRawMaterial")
    val esMateriaPrimaProducto: Boolean? = null,
    @Column("ESSERVICIO_PRODUCTO")
    @JsonProperty("isService")
    val esServicioProducto: Boolean? = null,
    @Column("FICHA_PRODUCTO")
    @JsonProperty("productSheet")
    val fichaProducto: String? = null,
    @Column("FECHAREGISTRO_PRODUCTO")
    @JsonProperty("registrationDate")
    val fechaRegistroProducto: LocalDateTime? = null,
    @Column("FECHAMODIFICACION_PRODUCTO")
    @JsonProperty("modificationDate")
    val fechaModificacionProducto: LocalDateTime? = null,
    @Column("ESTADO_PRODUCTO")
    @JsonProperty("productStatus")
    val estadoProducto: Boolean? = null,
)
