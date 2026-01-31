package erp.base.api.models.rrh

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("CUENTABANCARIA")
data class CuentaBancaria(
    @Id
    @Column("ID_CUENTABANCARIA")
    @JsonProperty("countBankId")
    val idCuentaBancaria: Int? = null,

    @Column("ID_BANCOCATALOGO")
    @JsonProperty("catalogBank")
    val idCatalogoBanco: Int? = null,

    @Column("ID_EMPLEADO")
    @JsonProperty("employeeId")
    val idEmpleado: Int? = null,

    @Column("ID_TIPOCUENTABANCARIA")
    @JsonProperty("bankAccountTypeId")
    val idTipoCuentaBancaria: Int? = null,

    @Column("NUMERO_CUENTABANCARIA")
    @JsonProperty("numberCountBank")
    val numeroCuentaBancaria: String? = null,

    @Column("ESTADO_CUENTABANCARIA")
    @JsonProperty("countStateBank")
    val estadoCuentaBancaria: Boolean? = null,
)
