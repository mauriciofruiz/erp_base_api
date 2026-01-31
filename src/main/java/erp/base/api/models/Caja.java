package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("CAJA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Caja {
    @Id
    @Column("ID_CAJA")
    @JsonProperty("cashBoxId")
    private Integer idCaja;

    @Column("ID_CUENTA")
    @JsonProperty("accountId")
    private Integer idCuenta;

    @Column("ID_SUCURSAL")
    @JsonProperty("branchId")
    private Integer idSucursal;

    @Column("NOMBRE_CAJA")
    @JsonProperty("cashBoxName")
    private String nombreCaja;

    @Column("NUMERO_CAJA")
    @JsonProperty("cashBoxNumber")
    private Integer numeroCaja;

    @Column("SECFAC_CAJA")
    @JsonProperty("cashBoxInvoiceSeq")
    private Integer secuenciaFactura;

    @Column("SECGUIA_CAJA")
    @JsonProperty("cashBoxGuideSeq")
    private Integer secuenciaGuia;

    @Column("SECNC_CAJA")
    @JsonProperty("cashBoxNcSeq")
    private Integer secuenciaNc;

    @Column("SECND_CAJA")
    @JsonProperty("cashBoxNdSeq")
    private Integer secuenciaNd;

    @Column("SECRET_CAJA")
    @JsonProperty("cashBoxRetSeq")
    private Integer secuenciaRetencion;

    @Column("ESTADO_CAJA")
    @JsonProperty("cashBoxState")
    private Boolean estadoCaja;

    @Column("SECLIQ_CAJA")
    @JsonProperty("cashBoxLiquidationSeq")
    private Integer secuenciaLiquidacion;
}
