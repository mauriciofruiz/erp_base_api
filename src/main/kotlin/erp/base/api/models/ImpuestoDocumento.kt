package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table("impuesto_documento")
data class ImpuestoDocumento(
    @Id
    @Column("id_impuesto")
    @JsonProperty("taxId")
    val idImpuesto: Int,
    @Column("id_documento")
    @JsonProperty("documentId")
    val idDocumento: Long? = null,
    @Column("id_tipo_documento")
    @JsonProperty("documentTypeId")
    val idTipoDocumento: Int? = null,
    @Column("id_voucher")
    @JsonProperty("voucherId")
    val idVoucher: Int? = null,
    @Column("porcentaje_impuesto")
    @JsonProperty("taxPercentage")
    val porcentajeImpuesto: BigDecimal? = null,
    @Column("descripcion_impuesto")
    @JsonProperty("taxDescription")
    val descripcionImpuesto: String? = null,
    @Column("codigo_impuesto")
    @JsonProperty("taxCode")
    val codigoImpuesto: String? = null,
    @Column("id_cuenta")
    @JsonProperty("accountId")
    val idCuenta: Int? = null,
    @Column("base_impuesto")
    @JsonProperty("taxBase")
    val baseImpuesto: BigDecimal? = null,
    @Column("monto_impuesto")
    @JsonProperty("taxAmount")
    val montoImpuesto: BigDecimal? = null,
    @Column("estado_impuesto")
    @JsonProperty("taxState")
    val estadoImpuesto: Boolean? = null,
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
