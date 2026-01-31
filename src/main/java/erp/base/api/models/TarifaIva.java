package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Table("TARIFAIVA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TarifaIva extends BaseEntity {
    @Id
    @Column("ID_TARIFAIVA")
    @JsonProperty("taxRateId")
    private Integer idTarifaIva;

    @Column("PORC_TARIFAIVA")
    @JsonProperty("taxRatePercentage")
    private BigDecimal porcentajeTarifaIva;

    @Column("COD_TARIFAIVA")
    @JsonProperty("taxRateCode")
    private String codigoTarifaIva;

    @Column("descripcion_iva")
    @JsonProperty("taxDescription")
    private String descripcionIva;

    @Column("id_cuenta_venta")
    @JsonProperty("saleAccountId")
    private Integer idCuentaVenta;

    @Column("id_cuenta_compra")
    @JsonProperty("purchaseAccountId")
    private Integer idCuentaCompra;

    @Column("estado_tarifaiva")
    @JsonProperty("taxRateState")
    private Boolean estadoTarifaIva;
}
