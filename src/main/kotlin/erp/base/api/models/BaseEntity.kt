package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import java.time.LocalDateTime

abstract class BaseEntity(
    @CreatedBy
    @Column("id_usuario_creacion")
    @JsonProperty("creationUserId")
    open val idUsuarioCreacion: Int? = null,

    @LastModifiedBy
    @Column("id_usuario_modificacion")
    @JsonProperty("modificationUserId")
    open val idUsuarioModificacion: Int? = null,

    @CreatedDate
    @Column("fecha_creacion")
    @JsonProperty("registrationDate")
    open val fechaCreacion: LocalDateTime? = null,

    @LastModifiedDate
    @Column("fecha_modificacion")
    @JsonProperty("modificationDate")
    open val fechaModificacion: LocalDateTime? = null,
)
