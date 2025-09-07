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

@Table("trb_retencion")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Retencion {
    @Id
    @Column("id")
    @JsonProperty("retentionId")
    private Integer idRetencion;

    @Column("id_tipo_retencion")
    @JsonProperty("retentionTypeId")
    private Integer idTipoRetencion;

    @Column("id_cuenta")
    @JsonProperty("accountId")
    private Integer idCuenta;

    @Column("detalle")
    @JsonProperty("detail")
    private String detalle;

    @Column("valor_porcentaje")
    @JsonProperty("percentageValue")
    private BigDecimal valorPorcentaje;

    @Column("porcentaje_descripcion")
    @JsonProperty("percentageDescription")
    private String porcentajeDescripcion;

    @Column("campo_extra")
    @JsonProperty("extraField")
    private String campoExtra;

    @Column("codigo_anexo")
    @JsonProperty("annexCode")
    private String codigoAnexo;

    @Column("es_proveedor")
    @JsonProperty("isProvider")
    private Boolean esProveedor;

    @Column("es_cliente")
    @JsonProperty("isClient")
    private Boolean esCliente;

    @Column("estado_retencion")
    @JsonProperty("retentionState")
    private Boolean estadoRetencion;
}
