package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal

@Table("BANCO")
data class Banco(
    @Id
    @Column("ID_BANCO")
    @JsonProperty("bankId")
    val idBanco: Int? = null,

    @Column("ID_CUENTA")
    @JsonProperty("accountId")
    val idCuenta: Int? = null,

    @Column("ID_TIPOCUENTABANCARIA")
    @JsonProperty("bankAccountTypeId")
    val idTipoCuentaBancaria: Int? = null,

    @Column("NOMBRE_BANCO")
    @JsonProperty("bankName")
    val nombreBanco: String? = null,

    @Column("NUMEROCUENTA_BANCO")
    @JsonProperty("bankAccountNumber")
    val numeroCuentaBanco: String? = null,

    @Column("SALDODISPONIBLE_BANCO")
    @JsonProperty("availableBalance")
    val saldoDisponibleBanco: BigDecimal? = null,

    @Column("SALDOCONTABLE_BANCO")
    @JsonProperty("accountBalance")
    val saldoContableBanco: BigDecimal? = null,

    @Column("CONTACTO_BANCO")
    @JsonProperty("bankContact")
    val contactoBanco: String? = null,

    @Column("TITULOCONTACTO_BANCO")
    @JsonProperty("bankContactTitle")
    val tituloContactoBanco: String? = null,

    @Column("TELEFONOCONTACTO_BANCO")
    @JsonProperty("bankContactPhone")
    val telefonoContactoBanco: String? = null,

    @Column("NUMCHEQUEIMP_BANCO")
    @JsonProperty("printedCheckNumber")
    val numeroChequeImpresoBanco: Int? = null,

    @Column("ULTIMONUMCHEQUE_BANCO")
    @JsonProperty("lastCheckNumber")
    val ultimoNumeroChequeBanco: Int? = null,

    @Column("ESCAJACHICA_BANCO")
    @JsonProperty("isPettyCash")
    val esCajaChicaBanco: Boolean? = null,

    @Column("ESTADO_BANCO")
    @JsonProperty("bankState")
    val estadoBanco: Boolean? = null,

    @Column("CHEQUERECUPERADO_BANCO")
    @JsonProperty("recoveredCheck")
    val chequeRecuperadoBanco: String? = null,

    @Column("TXTDEPOSITO_BANCO")
    @JsonProperty("depositText")
    val textoDepositoBanco: String? = null,

    @Column("TXTTARJETA_BANCO")
    @JsonProperty("cardText")
    val textoTarjetaBanco: String? = null,

    @Column("SALDOINICIAL_BANCO")
    @JsonProperty("initialBalance")
    val saldoInicialBanco: BigDecimal? = null,

    @Column("ID_SUCURSAL")
    @JsonProperty("branchId")
    val idSucursal: Int? = null,
)
