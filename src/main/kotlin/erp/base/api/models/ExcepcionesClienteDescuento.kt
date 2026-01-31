package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.ZonedDateTime

@Table("excepciones_cliente_descuento")
data class ExcepcionesClienteDescuento(
    @Id
    @Column("id_excepcion")
    @JsonProperty("exceptionId")
    val idExcepcion: Int? = null,

    @Column("id_cliente")
    @JsonProperty("clientId")
    val idCliente: Long? = null,

    @Column("id_producto")
    @JsonProperty("productId")
    val idProducto: Int? = null,

    @Column("id_subgrupo")
    @JsonProperty("subgroupId")
    val idSubgrupo: Int? = null,

    @Column("id_grupo")
    @JsonProperty("groupId")
    val idGrupo: Int? = null,

    @Column("porcion_descuento")
    @JsonProperty("discountPortion")
    val porcionDescuento: BigDecimal? = null,

    @Column("fecha_inicio")
    @JsonProperty("startDate")
    val fechaInicio: ZonedDateTime? = null,

    @Column("fecha_fin")
    @JsonProperty("endDate")
    val fechaFin: ZonedDateTime? = null,

    @Column("estado")
    @JsonProperty("status")
    val estado: Boolean? = null,

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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as ExcepcionesClienteDescuento
        return idExcepcion == other.idExcepcion
    }

    override fun hashCode(): Int = idExcepcion?.hashCode() ?: 0
}
