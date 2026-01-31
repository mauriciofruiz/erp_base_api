package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("BANCOCATALOGO")
data class BancoCatalogo(
    @Id
    @Column("ID_BANCOCATALOGO")
    @JsonProperty("bankCatalogId")
    val idBancoCatalogo: Int? = null,

    @Column("NOMBRE_BANCOCATALOGO")
    @JsonProperty("bankCatalogName")
    val nombreBancoCatalogo: String? = null,

    @Column("CODIGO_BANCOCATALOGO")
    @JsonProperty("bankCatalogCode")
    val codigoBancoCatalogo: String? = null,

    @Column("estado_banco")
    @JsonProperty("bankStatus")
    val estadoBanco: Boolean? = null,

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
