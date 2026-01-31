package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("AREA")
data class Area(
    @Id
    @Column("ID_AREA")
    @JsonProperty("areaId")
    val idArea: Int? = null,
    @Column("NOMBRE_AREA")
    @JsonProperty("areaName")
    val nombreArea: String? = null,
    @Column("DESCRIPCION_AREA")
    @JsonProperty("areaDescription")
    val descripcionArea: String? = null,
)
