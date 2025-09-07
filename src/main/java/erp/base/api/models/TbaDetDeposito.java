package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_DETDEPOSITO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaDetDeposito {
    @Id
    @Column("ID_DETDEPOSITO")
    @JsonProperty("detailDepositId")
    private Integer idDetDeposito;

    @Column("ID_MOTIVO")
    @JsonProperty("reasonId")
    private Integer idMotivo;

    @Column("ID_DEPOSITO")
    @JsonProperty("depositId")
    private Integer idDeposito;

    @Column("ID_CUENTA")
    @JsonProperty("accountId")
    private Integer idCuenta;

    @Column("MONTO_DETDEPOSITO")
    @JsonProperty("depositAmount")
    private Double montoDetDeposito;

    @Column("OBS_DETDEPOSITO")
    @JsonProperty("depositObservation")
    private String obsDetDeposito;

    @Column("ANULADO_DETDEPOSITO")
    @JsonProperty("depositCancelled")
    private Boolean anuladoDetDeposito;

    @Column("CODEMPLEADO_DETDEPOSITO")
    @JsonProperty("employeeCode")
    private Integer codEmpleadoDetDeposito;

    @Column("CODIMPORTACION_DETDEPOSITO")
    @JsonProperty("importCode")
    private String codImportacionDetDeposito;

    @Column("CODPROYECTO_DETDEPOSITO")
    @JsonProperty("projectCode")
    private String codProyectoDetDeposito;

    @Column("CODSUCURSAL_DETDEPOSITO")
    @JsonProperty("branchCode")
    private Integer codSucursalDetDeposito;

    @Column("CODANTICIPO_DETDEPOSITO")
    @JsonProperty("advanceCode")
    private String codAnticipoDetDeposito;

    @Column("SALDOANTICIPO_DETDEPOSITO")
    @JsonProperty("advanceBalance")
    private Double saldoAnticipoDetDeposito;

    @Column("VALCRUZADO_DETDEPOSITO")
    @JsonProperty("crossedValue")
    private Double valCruzadoDetDeposito;

    @Column("CODCLIENTE_DETDEPOSITO")
    @JsonProperty("customerCode")
    private Integer codClienteDetDeposito;

    @Column("CODPROVEEDOR_DETDEPOSITO")
    @JsonProperty("providerCode")
    private Integer codProveedorDetDeposito;
}
