package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("EMPRESA")
data class Empresa(
    @Id
    @Column("EMP_ID")
    @JsonProperty("companyId")
    val id: Long,
    @Column("EMP_CODIGO")
    @JsonProperty("companyCode")
    val codigo: String? = null,
    @Column("EMP_RAZONSOCIAL")
    @JsonProperty("companyName")
    val razonSocial: String? = null,
    @Column("EMP_CIUDAD")
    @JsonProperty("companyCity")
    val ciudad: String? = null,
    @Column("EMP_DIRECCION")
    @JsonProperty("companyAddress")
    val direccion: String? = null,
    @Column("EMP_RUCEMPRESA")
    @JsonProperty("companyRuc")
    val ruc: String? = null,
    @Column("EMP_TELEFONO")
    @JsonProperty("companyPhone")
    val telefono: String? = null,
    @Column("EMP_CORREOELECTRONICO")
    @JsonProperty("companyEmail")
    val email: String? = null,
    @Column("EMP_REPRESENTANTE")
    @JsonProperty("companyRepresentative")
    val representante: String? = null,
    @Column("EMP_RUCREPRESENTANTE")
    @JsonProperty("representativeRuc")
    val rucRepresentante: String? = null,
    @Column("EMP_NUMDECIMALESPVP")
    @JsonProperty("decimalNumberPvp")
    val numeroDecimalesPvp: Int? = null,
    @Column("EMP_OBLIGADOCONTABILIDAD")
    @JsonProperty("accountingMandatory")
    val obligadoContabilidad: Boolean? = null,
    @Column("EMP_CONTRIBUYENTEESPECIAL")
    @JsonProperty("specialContributor")
    val contribuyenteEspecial: String? = null,
    @Column("EMP_PIEFIRMAFE")
    @JsonProperty("footerSignatureFe")
    val pieFirmaFe: String? = null,
    @Column("EMP_AGENTERETIENE")
    @JsonProperty("agentRetains")
    val agenteRetiene: Boolean? = null,
    @Column("EMP_RESOLUCIONAGENTE")
    @JsonProperty("resolutionAgent")
    val resolucionAgente: String? = null,
    @Column("EMP_HOST")
    @JsonProperty("host")
    val host: String? = null,
)
