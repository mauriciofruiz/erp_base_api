package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("DETDEPOSITO")
data class DetDeposito(
    @Id
    @Column("ID_DETDEPOSITO")
    @JsonProperty("detailDepositId")
    val idDetDeposito: Int,
    @Column("ID_MOTIVO")
    @JsonProperty("reasonId")
    val idMotivo: Int? = null,
    @Column("ID_DEPOSITO")
    @JsonProperty("depositId")
    val idDeposito: Int? = null,
    @Column("ID_CUENTA")
    @JsonProperty("accountId")
    val idCuenta: Int? = null,
    @Column("MONTO_DETDEPOSITO")
    @JsonProperty("depositAmount")
    val montoDetDeposito: Double? = null,
    @Column("OBS_DETDEPOSITO")
    @JsonProperty("depositObservation")
    val obsDetDeposito: String? = null,
    @Column("ANULADO_DETDEPOSITO")
    @JsonProperty("depositCancelled")
    val anuladoDetDeposito: Boolean? = null,
    @Column("CODEMPLEADO_DETDEPOSITO")
    @JsonProperty("employeeCode")
    val codEmpleadoDetDeposito: Int? = null,
    @Column("CODIMPORTACION_DETDEPOSITO")
    @JsonProperty("importCode")
    val codImportacionDetDeposito: String? = null,
    @Column("CODPROYECTO_DETDEPOSITO")
    @JsonProperty("projectCode")
    val codProyectoDetDeposito: String? = null,
    @Column("CODSUCURSAL_DETDEPOSITO")
    @JsonProperty("branchCode")
    val codSucursalDetDeposito: Int? = null,
    @Column("CODANTICIPO_DETDEPOSITO")
    @JsonProperty("advanceCode")
    val codAnticipoDetDeposito: String? = null,
    @Column("SALDOANTICIPO_DETDEPOSITO")
    @JsonProperty("advanceBalance")
    val saldoAnticipoDetDeposito: Double? = null,
    @Column("VALCRUZADO_DETDEPOSITO")
    @JsonProperty("crossedValue")
    val valCruzadoDetDeposito: Double? = null,
    @Column("CODCLIENTE_DETDEPOSITO")
    @JsonProperty("customerCode")
    val codClienteDetDeposito: Int? = null,
    @Column("CODPROVEEDOR_DETDEPOSITO")
    @JsonProperty("providerCode")
    val codProveedorDetDeposito: Int? = null,
)
