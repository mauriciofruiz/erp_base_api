package erp.base.api.models;

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

@Table("INV_PRODUCTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InvProducto {
    @Id
    @Column("ID_PRODUCTO")
    @JsonProperty("productId")
    private Integer idProducto;

    @Column("ID_ROTACIONPRODUCTO")
    @JsonProperty("rotationProductId")
    private Integer idRotacionProducto;

    @Column("ID_SUBGRUPO")
    @JsonProperty("subgroupId")
    private Integer idSubgrupo;

    @Column("ID_TARIFAIVA")
    @JsonProperty("tarifaIvaId")
    private Integer idTarifaIva;

    @Column("ID_UNIDADMEDIDA")
    @JsonProperty("unidadMedidaId")
    private Integer idUnidadMedida;

    @Column("COD_PRODUCTO")
    @JsonProperty("productCode")
    private String codProducto;

    @Column("NOMBRECORTO_PRODUCTO")
    @JsonProperty("shortProductName")
    private String nombreCortoProducto;

    @Column("NOMBRELARGO_PRODUCTO")
    @JsonProperty("longProductName")
    private String nombreLargoProducto;

    @Column("COSTOUNITARIO_PRODUCTO")
    @JsonProperty("unitCost")
    private BigDecimal costoUnitarioProducto;

    @Column("PVPUNITARIO_PRODUCTO")
    @JsonProperty("unitPrice")
    private BigDecimal pvpUnitarioProducto;

    @Column("MARCAIVA_PRODUCTO")
    @JsonProperty("isVATApplicable")
    private Boolean marcaIvaProducto;

    @Column("CARACTERISTICA_PRODUCTO")
    @JsonProperty("productCharacteristics")
    private String caracteristicaProducto;

    @Column("PESOKG_PRODUCTO")
    @JsonProperty("productWeight")
    private BigDecimal pesoKgProducto;

    @Column("LARGO_PRODUCTO")
    @JsonProperty("productLength")
    private BigDecimal largoProducto;

    @Column("ANCHO_PRODUCTO")
    @JsonProperty("productWidth")
    private BigDecimal anchoProducto;

    @Column("ESPESOR_PRODUCTO")
    @JsonProperty("productThickness")
    private BigDecimal espesorProducto;

    @Column("MARCA_PRODUCTO")
    @JsonProperty("productBrand")
    private String marcaProducto;

    @Column("PRECIOFACTURA_PRODUCTO")
    @JsonProperty("invoicePrice")
    private BigDecimal precioFacturaProducto;

    @Column("ESMATERIAPRIMA_PRODUCTO")
    @JsonProperty("isRawMaterial")
    private Boolean esMateriaPrimaProducto;

    @Column("ESSERVICIO_PRODUCTO")
    @JsonProperty("isService")
    private Boolean esServicioProducto;

    @Column("FICHA_PRODUCTO")
    @JsonProperty("productSheet")
    private String fichaProducto;

    @Column("FECHAREGISTRO_PRODUCTO")
    @JsonProperty("registrationDate")
    private LocalDateTime fechaRegistroProducto;

    @Column("FECHAMODIFICACION_PRODUCTO")
    @JsonProperty("modificationDate")
    private LocalDateTime fechaModificacionProducto;

    @Column("ESTADO_PRODUCTO")
    @JsonProperty("productStatus")
    private Boolean estadoProducto;
}
