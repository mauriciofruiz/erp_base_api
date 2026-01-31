package erp.base.api.models.rrh

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("CARGAFAMILIAR")
data class CargaFamiliar(
    @Id
    @Column("ID_CARGAFML")
    @JsonProperty("familyBurdenId")
    val idCargaFml: Int? = null,

    @Column("ID_PARENTESCO")
    @JsonProperty("kinId")
    val idParentesco: Int? = null,

    @Column("ID_EMPLEADO")
    @JsonProperty("employeeId")
    val idEmpleado: Int? = null,

    @Column("NOMBRE_CARGAFML")
    @JsonProperty("familyBurdenName")
    val nombreCargaFml: String? = null,

    @Column("CEDULA_CARGAFML")
    @JsonProperty("identificationFML")
    val cedulaCargaFml: String? = null,

    @Column("FECHANACIMIENTO_CARGAFML")
    @JsonProperty("birthdateBurdenFml")
    val fechaNacimientoCargaFml: LocalDate? = null,

    @Column("CAPACIDADESPECIAL_CARGAFML")
    @JsonProperty("capacityEspecialBurdenFml")
    val capacidadEspecialCargaFml: Boolean? = null,

    @Column("ARCHIVO_CARGAFML")
    @JsonProperty("fileBurdenFml")
    val archivoCargaFml: String? = null,

    @Column("ACTIVA_CARGAFML")
    @JsonProperty("enableBurdenFml")
    val activaCargaFml: Boolean? = null,
)
