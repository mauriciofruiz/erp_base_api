package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("TIPOCUENTABANCARIA")
data class TipoCuentaBancaria(
    @Id
    @Column("ID_TIPOCUENTABANCARIA")
    @JsonProperty("accountTypeId")
    val idTipoCuentaBancaria: Int? = null,

    @Column("NOMBRE_TIPOCUENTABANCARIA")
    @JsonProperty("accountTypeName")
    val nombreTipoCuentaBancaria: String? = null,

    @Column("NEMONICO_TIPOCUENTABANCARIA")
    @JsonProperty("accountTypeMnemonic")
    val nemonicoTipoCuentaBancaria: String? = null,
)
