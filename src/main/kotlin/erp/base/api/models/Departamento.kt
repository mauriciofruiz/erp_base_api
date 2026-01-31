package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("DEPARTAMENTO")
data class Departamento(
    @Id
    @Column("ID_DEPARTAMENTO")
    @JsonProperty("departmentId")
    val idDepartamento: Int? = null,

    @Column("NOMBRE_DEPARTAMENTO")
    @JsonProperty("departmentName")
    val nombreDepartamento: String? = null,

    @Column("ESTADO_DEPARTAMENTO")
    @JsonProperty("departmentStatus")
    val estadoDepartamento: Boolean? = null,

    @Column("ID_AREAGRUPO")
    @JsonProperty("areaGroupId")
    val idAreaGrupo: Int? = null,
)
