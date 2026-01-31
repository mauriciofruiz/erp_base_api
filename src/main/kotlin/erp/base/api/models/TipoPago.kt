package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("TIPOPAGO")
data class TipoPago(
    @Id
    @Column("ID_TIPOPAGO")
    @JsonProperty("paymentTypeId")
    val idTipoPago: Int,

    @Column("COD_TIPOPAGO")
    @JsonProperty("paymentTypeCode")
    val codigoTipoPago: String? = null,

    @Column("NOMBRE_TIPOPAGO")
    @JsonProperty("paymentTypeName")
    val nombreTipoPago: String? = null,
)
