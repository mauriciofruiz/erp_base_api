package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.ZonedDateTime

@Table("BITACORA")
data class Bitacora(
    @Id
    @Column("ID_BITACORA")
    @JsonProperty("logId")
    val idBitacora: Int,
    @Column("ID_USR")
    @JsonProperty("userId")
    val idUsuario: Int? = null,
    @NotNull(message = "El identificador es requerido")
    @Column("IDENTIFICADOR_BITACORA")
    @JsonProperty("identifier")
    val identificador: Int,
    @Column("TABLE_BITACORA")
    @JsonProperty("table")
    val tabla: String? = null,
    @Column("CAMPO_BITACORA")
    @JsonProperty("field")
    val campo: String? = null,
    @Column("OLDVAL_BITACORA")
    @JsonProperty("oldValue")
    val oldValue: String? = null,
    @Column("NEWVAL_BITACORA")
    @JsonProperty("newValue")
    val newValue: String? = null,
    @Column("FECHA_BITACORA")
    @JsonProperty("date")
    var fecha: ZonedDateTime? = null,
    @Column("IPDISPOSITIVO_BITACORA")
    @JsonProperty("deviceIp")
    var ipDispositivo: String? = null,
    @Column("NOMBREDISPOSITIVO_BITACORA")
    @JsonProperty("deviceName")
    var nombreDispositivo: String? = null,
)
