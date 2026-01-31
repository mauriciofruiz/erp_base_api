package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("TIPOCOMPROBANTE")
data class TipoComprobante(
    @Id
    @Column("ID_TIPOCOMPROBANTE")
    @JsonProperty("voucherTypeId")
    val idTipoComprobante: Int,
    @Column("NOMBRE_TIPOCOMPROBANTE")
    @JsonProperty("voucherTypeName")
    val nombreTipoComprobante: String? = null,
    @Column("COD_TIPOCOMPROBANTE")
    @JsonProperty("voucherTypeCode")
    val codigoTipoComprobante: String? = null,
)
