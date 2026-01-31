package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("TIPOCUENTA")
data class TipoCuenta(
    @Id
    @Column("ID_TIPOCUENTA")
    @JsonProperty("accountTypeId")
    val idTipoCuenta: Int,
    @Column("NOMBRE_TIPOCUENTA")
    @JsonProperty("accountTypeName")
    val nombreTipoCuenta: String? = null,
    @Column("DESCRIPCION_TIPOCUENTA")
    @JsonProperty("accountTypeDescription")
    val descripcionTipoCuenta: String? = null,
)
