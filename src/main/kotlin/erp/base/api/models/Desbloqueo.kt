package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("DESBLOQUEO")
data class Desbloqueo(
    @Id
    @Column("ID_DESBLOQUEO")
    @JsonProperty("unlockId")
    val idDesbloqueo: Int,
    @Column("IP_DESBLOQUEO")
    @JsonProperty("unlockIp")
    val ipDesbloqueo: String? = null,
    @Column("DESCRIPCION_DESBLOQUEO")
    @JsonProperty("unlockDescription")
    val descripcionDesbloqueo: String? = null,
    @Column("ESTADO_DESBLOQUEO")
    @JsonProperty("unlockStatus")
    val estadoDesbloqueo: Boolean? = null,
)
