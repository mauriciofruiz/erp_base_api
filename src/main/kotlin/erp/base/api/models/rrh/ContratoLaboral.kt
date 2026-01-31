package erp.base.api.models.rrh

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDate

@Table("CONTRATOLABORAL")
data class ContratoLaboral(
    @Id
    @Column("ID_CONTRATO")
    @JsonProperty("contractId")
    var idContrato: Int? = null,

    @Column("ID_TIPOCONTRATO")
    @JsonProperty("contractTypeId")
    var idTipoContrato: Int? = null,

    @Column("ID_MODALIDADCONTRATO")
    @JsonProperty("contractModeId")
    var idModalidadContrato: Int? = null,

    @Column("ID_EMPLEADO")
    @JsonProperty("employeeId")
    var idEmpleado: Int? = null,

    @Column("FECHAINICIO_CONTRATO")
    @JsonProperty("contractStartDate")
    var fechaInicioContrato: LocalDate? = null,

    @Column("FINPRUEBA_CONTRATO")
    @JsonProperty("contractProbationEndDate")
    var finPruebaContrato: LocalDate? = null,

    @Column("FECHAFIN_CONTRATO")
    @JsonProperty("contractEndDate")
    var fechaFinContrato: LocalDate? = null,

    @Column("ARCHIVO_CONTRATO")
    @JsonProperty("contractFile")
    var archivoContrato: ByteArray? = null,

    @Column("ESTADO_CONTRATO")
    @JsonProperty("contractStatus")
    var estadoContrato: Boolean? = null,

    @Column("NROHORAS_CONTRATO")
    @JsonProperty("contractHours")
    var numeroHorasContrato: BigDecimal? = null,
)
