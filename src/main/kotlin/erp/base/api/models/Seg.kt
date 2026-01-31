package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("SEG")
data class Seg(
    @Id
    @Column("ID_SEG")
    @JsonProperty("segId")
    val idSeg: Int,
    @Column("TIEMPOSESION_SEG")
    @JsonProperty("sessionTime")
    val tiempoSesionSeg: String? = null,
    @Column("HORADESDE_SEG")
    @JsonProperty("startTime")
    val horaDesdeSeg: String? = null,
    @Column("HORAHASTA_SEG")
    @JsonProperty("endTime")
    val horaHastaSeg: String? = null,
)
