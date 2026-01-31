package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("MATRICULA")
data class Matricula(
    @Id
    @Column("ID_MATRICULA")
    @JsonProperty("registrationId")
    val idMatricula: Int,
    @Column("ID_VEHICULO")
    @JsonProperty("vehicleId")
    val idVehiculo: Int? = null,
    @Column("FECHAEMISION_MATRICULA")
    @JsonProperty("registrationIssueDate")
    val fechaEmisionMatricula: LocalDate? = null,
    @Column("FECHACADUCA_MATRICULA")
    @JsonProperty("registrationExpirationDate")
    val fechaCaducaMatricula: LocalDate? = null,
)
