package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal

@Table("retencion")
data class Retencion(
    @Id
    @Column("id")
    @JsonProperty("retentionId")
    val idRetencion: Int? = null,

    @Column("id_tipo_retencion")
    @JsonProperty("retentionTypeId")
    val idTipoRetencion: Int? = null,

    @Column("id_cuenta")
    @JsonProperty("accountId")
    val idCuenta: Int? = null,

    @Column("detalle")
    @JsonProperty("detail")
    val detalle: String? = null,

    @Column("valor_porcentaje")
    @JsonProperty("percentageValue")
    val valorPorcentaje: BigDecimal? = null,

    @Column("porcentaje_descripcion")
    @JsonProperty("percentageDescription")
    val porcentajeDescripcion: String? = null,

    @Column("campo_extra")
    @JsonProperty("extraField")
    val campoExtra: String? = null,

    @Column("codigo_anexo")
    @JsonProperty("annexCode")
    val codigoAnexo: String? = null,

    @Column("es_proveedor")
    @JsonProperty("isProvider")
    val esProveedor: Boolean? = null,

    @Column("es_cliente")
    @JsonProperty("isClient")
    val esCliente: Boolean? = null,

    @Column("estado_retencion")
    @JsonProperty("retentionState")
    val estadoRetencion: Boolean? = null,
)
