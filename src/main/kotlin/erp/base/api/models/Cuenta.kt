package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDate

@Table("CUENTA")
data class Cuenta(
    @Id
    @Column("ID_CUENTA")
    @JsonProperty("accountId")
    val idCuenta: Int,
    @Column("ID_USR")
    @JsonProperty("userId")
    val idUsuario: Int? = null,
    @Column("ID_INDICEFINANCIERO")
    @JsonProperty("financialIndexId")
    val idIndiceFinanciero: Int? = null,
    @Column("ID_TIPOCUENTA")
    @JsonProperty("accountTypeId")
    val idTipoCuenta: Int? = null,
    @Column("NUMERO_CUENTA")
    @JsonProperty("accountNumber")
    val numeroCuenta: String? = null,
    @Column("NUMERO_CUENTAPADRE")
    @JsonProperty("parentAccountNumber")
    val numeroCuentaPadre: String? = null,
    @Column("NOMBRE_CUENTA")
    @JsonProperty("accountName")
    val nombreCuenta: String? = null,
    @Column("DESCRIPCION_CUENTA")
    @JsonProperty("accountDescription")
    val descripcionCuenta: String? = null,
    @Column("SALDOINICIAL_CUENTA")
    @JsonProperty("initialBalance")
    val saldoInicialCuenta: BigDecimal? = null,
    @Column("BALANCE_CUENTA")
    @JsonProperty("balance")
    val balanceCuenta: BigDecimal? = null,
    @Column("MANUAL_CUENTA")
    @JsonProperty("manual")
    val manualCuenta: Boolean? = null,
    @Column("AUTOMATICA_CUENTA")
    @JsonProperty("automatic")
    val automaticaCuenta: Boolean? = null,
    @Column("ESMANOBRA_CUENTA")
    @JsonProperty("isManeuver")
    val esManobraCuenta: Boolean? = null,
    @Column("TIENEACTIVOFIJO_CUENTA")
    @JsonProperty("hasFixedAsset")
    val tieneActivoFijoCuenta: Boolean? = null,
    @Column("OBLIGAPROYECTO_CUENTA")
    @JsonProperty("requiresProject")
    val obligaProyectoCuenta: Boolean? = null,
    @Column("OBLIGAEMPLEADO_CUENTA")
    @JsonProperty("requiresEmployee")
    val obligaEmpleadoCuenta: Boolean? = null,
    @Column("OBLIGADEPAR_CUENTA")
    @JsonProperty("requiresDepartment")
    val obligaDepartamentoCuenta: Boolean? = null,
    @Column("OBLIGAIMPORTACION_CUENTA")
    @JsonProperty("requiresImportation")
    val obligaImportacionCuenta: Boolean? = null,
    @Column("OBLIGASUCURSAL_CUENTA")
    @JsonProperty("requiresBranch")
    val obligaSucursalCuenta: Boolean? = null,
    @Column("OBLIGAINVENTARIO_CUENTA")
    @JsonProperty("requiresInventory")
    val obligaInventarioCuenta: Boolean? = null,
    @Column("OBLIGACLIENTE_CUENTA")
    @JsonProperty("requiresClient")
    val obligaClienteCuenta: Boolean? = null,
    @Column("OBLIGAPROVEEDOR_CUENTA")
    @JsonProperty("requiresprovider")
    val obligaProveedorCuenta: Boolean? = null,
    @Column("OBLIGACAJA_CUENTA")
    @JsonProperty("requiresCash")
    val obligaCajaCuenta: Boolean? = null,
    @Column("FECHACREACION_CUENTA")
    @JsonProperty("creationDate")
    val fechaCreacionCuenta: LocalDate? = null,
    @Column("FECHAMODIFICACION_CUENTA")
    @JsonProperty("modificationDate")
    val fechaModificacionCuenta: LocalDate? = null,
    @Column("ESTADO_CUENTA")
    @JsonProperty("accountStatus")
    val estadoCuenta: Boolean = false,
)
