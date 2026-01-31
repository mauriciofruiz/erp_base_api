package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("TIPOVEHICULO")
data class TipoVehiculo(
    @Id
    @Column("ID_TIPOVEHICULO")
    @JsonProperty("vehicleTypeId")
    var idTipoVehiculo: Int? = null,

    @Column("NOMBRE_TIPOVEHICULO")
    @JsonProperty("vehicleTypeName")
    var nombreTipoVehiculo: String? = null,

    @Column("id_usuario_creacion")
    @JsonProperty("creationUserId")
    val idUsuarioCreacion: Int? = null,

    @Column("id_usuario_modificacion")
    @JsonProperty("modificationUserId")
    val idUsuarioModificacion: Int? = null,

    @Column("fecha_creacion")
    @JsonProperty("registrationDate")
    val fechaCreacion: LocalDateTime? = null,

    @Column("fecha_modificacion")
    @JsonProperty("modificationDate")
    val fechaModificacion: LocalDateTime? = null,
)
