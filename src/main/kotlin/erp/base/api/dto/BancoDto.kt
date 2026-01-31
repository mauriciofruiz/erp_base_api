package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class BancoDto(
    @field:NotNull(message = "La cuenta no puede ser nulo")
    @JsonProperty("accountId")
    val idCuenta: Int? = null,
    @field:NotNull(message = "El tipo de cuenta no puede ser nulo")
    @JsonProperty("bankAccountTypeId")
    val idTipoCuentaBancaria: Int? = null,
    @field:NotBlank(message = "El nombre del blanco no puede estar vacía")
    @JsonProperty("bankName")
    val nombreBanco: String? = null,
    @field:NotBlank(message = "El número del banco no puede estar vacío")
    @JsonProperty("bankAccountNumber")
    val numeroCuentaBanco: String? = null,
    @field:NotNull(message = "El saldo disponible del banco no puede ser nulo")
    @JsonProperty("availableBalance")
    val saldoDisponibleBanco: BigDecimal? = null,
    @field:NotNull(message = "El saldo contable del banco no puede ser nulo")
    @JsonProperty("accountBalance")
    val saldoContableBanco: BigDecimal? = null,
    @JsonProperty("bankContact")
    val contactoBanco: String? = null,
    @JsonProperty("bankContactTitle")
    val tituloContactoBanco: String? = null,
    @JsonProperty("bankContactPhone")
    val telefonoContactoBanco: String? = null,
    @JsonProperty("printedCheckNumber")
    val numeroChequeImpresoBanco: Int? = null,
    @JsonProperty("lastCheckNumber")
    val ultimoNumeroChequeBanco: Int? = null,
    @JsonProperty("isPettyCash")
    val esCajaChicaBanco: Boolean? = false,
    @JsonProperty("bankState")
    val estadoBanco: Boolean? = true,
    @JsonProperty("depositText")
    val textoDepositoBanco: String? = null,
    @JsonProperty("cardText")
    val textoTarjetaBanco: String? = null,
    @field:Min(value = 0, message = "El saldo inicial del banco no puede ser negativo ni estar vacío")
    @JsonProperty("initialBalance")
    val saldoInicialBanco: BigDecimal? = null,
    @field:NotNull(message = "La sucursal no puede ser nulo")
    @JsonProperty("branchId")
    val idSucursal: Int? = null,
)
