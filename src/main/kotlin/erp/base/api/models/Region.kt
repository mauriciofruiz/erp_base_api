package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("REGION")
data class Region(
    @Id
    @Column("ID_REGION")
    @JsonProperty("regionId")
    val idRegion: Int? = null,

    @Column("NOMBRE_REGION")
    @JsonProperty("regionName")
    val nombreRegion: String? = null,
)
