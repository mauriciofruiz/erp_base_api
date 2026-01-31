package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table("CUENTA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ConCuenta {
    @Id
    @Column("ID_CUENTA")
    @JsonProperty("accountId")
    private Integer idCuenta;

    @Column("ID_USR")
    @JsonProperty("userId")
    private Integer idUsuario;

    @Column("ID_INDICEFINANCIERO")
    @JsonProperty("financialIndexId")
    private Integer idIndiceFinanciero;

    @Column("ID_TIPOCUENTA")
    @JsonProperty("accountTypeId")
    private Integer idTipoCuenta;

    @Column("NUMERO_CUENTA")
    @JsonProperty("accountNumber")
    private String numeroCuenta;

    @Column("NUMERO_CUENTAPADRE")
    @JsonProperty("parentAccountNumber")
    private String numeroCuentaPadre;

    @Column("NOMBRE_CUENTA")
    @JsonProperty("accountName")
    private String nombreCuenta;

    @Column("DESCRIPCION_CUENTA")
    @JsonProperty("accountDescription")
    private String descripcionCuenta;

    @Column("SALDOINICIAL_CUENTA")
    @JsonProperty("initialBalance")
    private BigDecimal saldoInicialCuenta;

    @Column("BALANCE_CUENTA")
    @JsonProperty("balance")
    private BigDecimal balanceCuenta;

    @Column("MANUAL_CUENTA")
    @JsonProperty("manual")
    private Boolean manualCuenta;

    @Column("AUTOMATICA_CUENTA")
    @JsonProperty("automatic")
    private Boolean automaticaCuenta;

    @Column("ESMANOBRA_CUENTA")
    @JsonProperty("isManeuver")
    private Boolean esManobraCuenta;

    @Column("TIENEACTIVOFIJO_CUENTA")
    @JsonProperty("hasFixedAsset")
    private Boolean tieneActivoFijoCuenta;

    @Column("OBLIGAPROYECTO_CUENTA")
    @JsonProperty("requiresProject")
    private Boolean obligaProyectoCuenta;

    @Column("OBLIGAEMPLEADO_CUENTA")
    @JsonProperty("requiresEmployee")
    private Boolean obligaEmpleadoCuenta;

    @Column("OBLIGADEPAR_CUENTA")
    @JsonProperty("requiresDepartment")
    private Boolean obligaDepartamentoCuenta;

    @Column("OBLIGAIMPORTACION_CUENTA")
    @JsonProperty("requiresImportation")
    private Boolean obligaImportacionCuenta;

    @Column("OBLIGASUCURSAL_CUENTA")
    @JsonProperty("requiresBranch")
    private Boolean obligaSucursalCuenta;

    @Column("OBLIGAINVENTARIO_CUENTA")
    @JsonProperty("requiresInventory")
    private Boolean obligaInventarioCuenta;

    @Column("OBLIGACLIENTE_CUENTA")
    @JsonProperty("requiresClient")
    private Boolean obligaClienteCuenta;

    @Column("OBLIGAPROVEEDOR_CUENTA")
    @JsonProperty("requiresprovider")
    private Boolean obligaProveedorCuenta;

    @Column("OBLIGACAJA_CUENTA")
    @JsonProperty("requiresCash")
    private Boolean obligaCajaCuenta;

    @Column("FECHACREACION_CUENTA")
    @JsonProperty("creationDate")
    private LocalDate fechaCreacionCuenta;

    @Column("FECHAMODIFICACION_CUENTA")
    @JsonProperty("modificationDate")
    private LocalDate fechaModificacionCuenta;

    @Column("ESTADO_CUENTA")
    @JsonProperty("accountStatus")
    private boolean estadoCuenta;
}
