package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("ADQUIRIENTE")
data class Adquiriente(
    @Id
    @Column("ID_ADQUIRIENTE")
    @JsonProperty("acquirerId")
    val idAdquiriente: Int,
    @Column("NOMBRE_ADQUIRIENTE")
    @JsonProperty("acquirerName")
    val nombreAdquiriente: String? = null,
    @Column("estado_adquiriente")
    @JsonProperty("acquirerStatus")
    val estadoAdquiriente: Boolean? = null,
    @CreatedBy
    @Column("id_usuario_creacion")
    @JsonProperty("creationUserId")
    override val idUsuarioCreacion: Int? = null,
    @LastModifiedBy
    @Column("id_usuario_modificacion")
    @JsonProperty("modificationUserId")
    override val idUsuarioModificacion: Int? = null,
    @CreatedDate
    @Column("fecha_creacion")
    @JsonProperty("registrationDate")
    override val fechaCreacion: LocalDateTime? = null,
    @LastModifiedDate
    @Column("fecha_modificacion")
    @JsonProperty("modificationDate")
    override val fechaModificacion: LocalDateTime? = null,
) : BaseEntity(
    idUsuarioCreacion = idUsuarioCreacion,
    idUsuarioModificacion = idUsuarioModificacion,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
)
