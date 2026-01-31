package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("PROYECTO")
data class Proyecto(
    @Id
    @Column("ID_PROYECTO")
    @JsonProperty("projectId")
    val idProyecto: Int,

    @Column("NOMBRE_PROYECTO")
    @JsonProperty("projectName")
    val nombreProyecto: String? = null,

    @Column("DESCRIPCION_PROYECTO")
    @JsonProperty("projectDescription")
    val descripcionProyecto: String? = null,

    @Column("FECHAINICIO_PROYECTO")
    @JsonProperty("projectStartDate")
    val fechaInicioProyecto: LocalDate? = null,

    @Column("FECHAFIN_PROYECTO")
    @JsonProperty("projectEndDate")
    val fechaFinProyecto: LocalDate? = null,

    @Column("ESTADO_PROYECTO")
    @JsonProperty("projectStatus")
    val estadoProyecto: Boolean? = null,
)
