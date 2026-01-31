package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.time.LocalDateTime

@Table("PERSONA")
data class Persona(
    @Id
    @Column("ID_PERSONA")
    @JsonProperty("personId")
    var idPersona: Int? = null,

    @Column("ID_TIPOCONTRIBUYENTE")
    @JsonProperty("contributorTypeId")
    var idTipoContribuyente: String? = null,

    @Column("ID_PAIS")
    @JsonProperty("countryId")
    var idPais: Int? = null,

    @Column("ID_PARROQUIA")
    @JsonProperty("parishId")
    var idParroquia: Int? = null,

    @Column("ID_ESTADOCIVIL")
    @JsonProperty("maritalStatusId")
    var idEstadoCivil: Int? = null,

    @Column("ID_GENERO")
    @JsonProperty("genderId")
    var idGenero: Int? = null,

    @Column("ID_IDENTIFICACIONFISCAL")
    @JsonProperty("taxIdentificationId")
    var idIdentificacionFiscal: Int? = null,

    @Column("IDENTIFICACION_PERSONA")
    @JsonProperty("personIdentification")
    var identificacionPersona: String? = null,

    @Column("NOMBRE_PERSONA")
    @JsonProperty("personName")
    var nombrePersona: String? = null,

    @Column("FECHANACIMIENTO_PERSONA")
    @JsonProperty("personBirthDate")
    var fechaNacimientoPersona: LocalDate? = null,

    @Column("DIRECCION_PERSONA")
    @JsonProperty("personAddress")
    var direccionPersona: String? = null,

    @Column("CELULAR_PERSONA")
    @JsonProperty("personCellphone")
    var celularPersona: String? = null,

    @Column("TELEFONO_PERSONA")
    @JsonProperty("personPhone")
    var telefonoPersona: String? = null,

    @Column("CORREO_PERSONA")
    @JsonProperty("personEmail")
    var correoPersona: String? = null,

    @Column("ESCLIENTE_PERSONA")
    @JsonProperty("isClient")
    var esClientePersona: Boolean? = null,

    @Column("ESEMPLEADO_PERSONA")
    @JsonProperty("isEmployee")
    var esEmpleadoPersona: Boolean? = null,

    @Column("ESPROVEEDOR_PERSONA")
    @JsonProperty("isProvider")
    var esProveedorPersona: Boolean? = null,

    @Column("FECHAREGISTRO_PERSONA")
    @JsonProperty("personRegistrationDate")
    var fechaRegistroPersona: LocalDateTime? = null,

    @Column("BARRIODOMICILIO_PERSONA")
    @JsonProperty("personResidenceNeighborhood")
    var barrioDomicilioPersona: String? = null,

    @Column("CANTONDOMICILIO_PERSONA")
    @JsonProperty("personResidenceCity")
    var cantonDomicilioPersona: String? = null,

    @Column("PARROQUIADOMICILIO_PERSONA")
    @JsonProperty("personResidenceParish")
    var parroquiaDomicilioPersona: String? = null,

    @Column("SECTORDOMICILIO_PERSONA")
    @JsonProperty("personResidenceSector")
    var sectorDomicilioPersona: String? = null,

    @Column("id_usuario_creacion")
    @JsonProperty("creationUserId")
    var idUsuarioCreacion: Int? = null,

    @Column("id_usuario_modificacion")
    @JsonProperty("modificationUserId")
    var idUsuarioModificacion: Int? = null,

    @Column("fecha_creacion")
    @JsonProperty("registrationDate")
    var fechaCreacion: LocalDateTime? = null,

    @Column("fecha_modificacion")
    @JsonProperty("modificationDate")
    var fechaModificacion: LocalDateTime? = null,
)
