package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("CARGO")
data class Cargo(
    @Id
    @Column("ID_CARGO")
    @JsonProperty("positionId")
    val idCargo: Int? = null,

    @Column("ID_DEPARTAMENTO")
    @JsonProperty("departmentId")
    val idDepartamento: Int? = null,

    @Column("NOMBRE_CARGO")
    @JsonProperty("positionName")
    val nombreCargo: String? = null,

    @Column("ESTADO_CARGO")
    @JsonProperty("positionState")
    val estadoCargo: Boolean? = null,
)
