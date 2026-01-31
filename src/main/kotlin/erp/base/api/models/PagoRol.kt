package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("PAGOROL")
data class PagoRol(
    @Id
    @Column("ID_PAGOROLES")
    @JsonProperty("paymentRoleId")
    val idPagoRoles: Int,
    @Column("ID_PAGO")
    @JsonProperty("paymentId")
    val idPago: Int? = null,
    @Column("ID_ROLPAGO")
    @JsonProperty("paymentRoleId")
    val idRolPago: Int? = null,
)
