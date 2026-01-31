package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Size
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table("TRANSPORTE")
data class Transporte(
    @Id
    @Column("ID_TRANSPORTE")
    @JsonProperty("transportId")
    var idTransporte: Int? = null,
    @Column("ID_TIPOVEHICULO")
    @JsonProperty("vehicleTypeId")
    var idTipoVehiculo: Int? = null,
    @Size(max = 50, message = "El nombre del contacto no puede tener más de 50 caracteres")
    @Column("NOMBRECONTACTO_TRANSPORTE")
    @JsonProperty("contactName")
    var nombreContacto: String? = null,
    @Size(max = 16, message = "La identificación del contacto no puede tener más de 16 caracteres")
    @Column("IDENTCONTACTO_TRANSPORTE")
    @JsonProperty("contactId")
    var identificacionContacto: String? = null,
    @Size(max = 200, message = "El número de contacto no puede tener más de 200 caracteres")
    @Column("NUMCONTACTO_TRANSPORTE")
    @JsonProperty("contactNumber")
    var numeroContacto: String? = null,
    @Size(max = 8, message = "La placa del transporte no puede tener más de 8 caracteres")
    @Column("PLACA_TRANSPORTE")
    @JsonProperty("licensePlate")
    var placaTransporte: String? = null,
    @Size(max = 50, message = "El sector del transporte no puede tener más de 50 caracteres")
    @Column("SECTOR_TRANSPORTE")
    @JsonProperty("sector")
    var sectorTransporte: String? = null,
    @Size(max = 50, message = "La provincia del transporte no puede tener más de 50 caracteres")
    @Column("PROVINCIA_TRANSPORTE")
    @JsonProperty("province")
    var provinciaTransporte: String? = null,
    @Size(max = 200, message = "El detalle del transporte no puede tener más de 200 caracteres")
    @Column("DET_TRANSPORTE")
    @JsonProperty("details")
    var detalleTransporte: String? = null,
    @Column("PESOMIN_TRANSPORTE")
    @JsonProperty("minWeight")
    var pesoMinimoTransporte: BigDecimal? = null,
    @Column("PESOMAX_TRANSPORTE")
    @JsonProperty("maxWeight")
    var pesoMaximoTransporte: BigDecimal? = null,
    @Size(max = 8, message = "La matrícula del transporte no puede tener más de 8 caracteres")
    @Column("MATRICULA_TRANSPORTE")
    @JsonProperty("registration")
    var matriculaTransporte: String? = null,
    @Column("INTERNO_TRANSPORTE")
    @JsonProperty("internal")
    var internoTransporte: Boolean? = null,
    @Column("INACTIVO_TRANSPORTE")
    @JsonProperty("inactive")
    var inactivoTransporte: Boolean? = null,
    @Column("id_tipoidentificacionfiscal")
    @JsonProperty("idTipoIdentificacionFiscal")
    var idTipoIdentificacionFiscal: String? = null,
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
