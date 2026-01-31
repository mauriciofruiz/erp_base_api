package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("CAJA")
data class Caja(
    @Id
    @Column("ID_CAJA")
    @JsonProperty("cashBoxId")
    val idCaja: Int? = null,

    @Column("ID_CUENTA")
    @JsonProperty("accountId")
    val idCuenta: Int? = null,

    @Column("ID_SUCURSAL")
    @JsonProperty("branchId")
    val idSucursal: Int? = null,

    @Column("NOMBRE_CAJA")
    @JsonProperty("cashBoxName")
    val nombreCaja: String? = null,

    @Column("NUMERO_CAJA")
    @JsonProperty("cashBoxNumber")
    val numeroCaja: Int? = null,

    @Column("SECFAC_CAJA")
    @JsonProperty("cashBoxInvoiceSeq")
    val secuenciaFactura: Int? = null,

    @Column("SECGUIA_CAJA")
    @JsonProperty("cashBoxGuideSeq")
    val secuenciaGuia: Int? = null,

    @Column("SECNC_CAJA")
    @JsonProperty("cashBoxNcSeq")
    val secuenciaNc: Int? = null,

    @Column("SECND_CAJA")
    @JsonProperty("cashBoxNdSeq")
    val secuenciaNd: Int? = null,

    @Column("SECRET_CAJA")
    @JsonProperty("cashBoxRetSeq")
    val secuenciaRetencion: Int? = null,

    @Column("ESTADO_CAJA")
    @JsonProperty("cashBoxState")
    val estadoCaja: Boolean? = null,

    @Column("SECLIQ_CAJA")
    @JsonProperty("cashBoxLiquidationSeq")
    val secuenciaLiquidacion: Int? = null,
)
