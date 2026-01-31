package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("BLOQUEO")
data class Bloqueo(
    @Id
    @Column("ID_BLOQUEO")
    @JsonProperty("blockId")
    val idBloqueo: Int,
    @Column("IP_BLOQUEO")
    @JsonProperty("blockIP")
    val ipBloqueo: String? = null,
    @Column("DESCRIPCION_BLOQUEO")
    @JsonProperty("blockDescription")
    val descripcionBloqueo: String? = null,
    @Column("ESTADO_BLOQUEO")
    @JsonProperty("blockState")
    val estadoBloqueo: Boolean? = null,
)
