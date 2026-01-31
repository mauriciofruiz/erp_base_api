package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDate

@Table("MOTIVODEPOSITO")
data class MotivoDeposito(
    @Id
    @Column("ID_MOTIVO")
    @JsonProperty("reasonId")
    val idMotivo: Int,
    @Column("ID_SUCURSAL")
    @JsonProperty("branchId")
    val idSucursal: Int? = null,
    @Column("NOMBRE_MOTIVO")
    @JsonProperty("reasonName")
    val nombreMotivo: String? = null,
    @Column("VALOR_MOTIVO")
    @JsonProperty("reasonValue")
    val valorMotivo: BigDecimal? = null,
    @Column("VALORPENDIENTE_MOTIVO")
    @JsonProperty("reasonPendingValue")
    val valorPendienteMotivo: BigDecimal? = null,
    @Column("ASIGNADO_MOTIVO")
    @JsonProperty("reasonAssigned")
    val asignadoMotivo: Boolean? = null,
    @Column("FECHA_MOTIVO")
    @JsonProperty("reasonDate")
    val fechaMotivo: LocalDate? = null,
    @Column("CODAPERTURA_MOTIVO")
    @JsonProperty("reasonOpeningCode")
    val codigoAperturaMotivo: String? = null,
    @Column("CODCAJA_MOTIVO")
    @JsonProperty("reasonBoxCode")
    val codigoCajaMotivo: String? = null,
    @Column("CODDETCOBRO_MOTIVO")
    @JsonProperty("reasonPaymentDetailCode")
    val codigoDetCobroMotivo: String? = null,
    @Column("ESTADO_MOTIVO")
    @JsonProperty("reasonState")
    val estadoMotivo: Boolean? = null,
    @Column("CODCOBRO_MOTIVO")
    @JsonProperty("reasonPaymentCode")
    val codigoCobroMotivo: Int? = null,
    @Column("CODCLIENTE_MOTIVO")
    @JsonProperty("reasonClientCode")
    val codigoClienteMotivo: Int? = null,
)
