package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table("PERSONA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class TbaPersona extends BaseEntity{
    @Id
    @Column("ID_PERSONA")
    @JsonProperty("personId")
    private Integer idPersona;

    @Column("ID_TIPOCONTRIBUYENTE")
    @JsonProperty("contributorTypeId")
    private String idTipoContribuyente;

    @Column("ID_PAIS")
    @JsonProperty("countryId")
    private Integer idPais;

    @Column("ID_PARROQUIA")
    @JsonProperty("parishId")
    private Integer idParroquia;

    @Column("ID_ESTADOCIVIL")
    @JsonProperty("maritalStatusId")
    private Integer idEstadoCivil;

    @Column("ID_GENERO")
    @JsonProperty("genderId")
    private Integer idGenero;

    @Column("ID_IDENTIFICACIONFISCAL")
    @JsonProperty("taxIdentificationId")
    private Integer idIdentificacionFiscal;

    @Column("IDENTIFICACION_PERSONA")
    @JsonProperty("personIdentification")
    private String identificacionPersona;

    @Column("NOMBRE_PERSONA")
    @JsonProperty("personName")
    private String nombrePersona;

    @Column("FECHANACIMIENTO_PERSONA")
    @JsonProperty("personBirthDate")
    private LocalDate fechaNacimientoPersona;

    @Column("DIRECCION_PERSONA")
    @JsonProperty("personAddress")
    private String direccionPersona;

    @Column("CELULAR_PERSONA")
    @JsonProperty("personCellphone")
    private String celularPersona;

    @Column("TELEFONO_PERSONA")
    @JsonProperty("personPhone")
    private String telefonoPersona;

    @Column("CORREO_PERSONA")
    @JsonProperty("personEmail")
    private String correoPersona;

    @Column("ESCLIENTE_PERSONA")
    @JsonProperty("isClient")
    private Boolean esClientePersona;

    @Column("ESEMPLEADO_PERSONA")
    @JsonProperty("isEmployee")
    private Boolean esEmpleadoPersona;

    @Column("ESPROVEEDOR_PERSONA")
    @JsonProperty("isProvider")
    private Boolean esProveedorPersona;

    @Column("FECHAREGISTRO_PERSONA")
    @JsonProperty("personRegistrationDate")
    private LocalDateTime fechaRegistroPersona;

    @Column("BARRIODOMICILIO_PERSONA")
    @JsonProperty("personResidenceNeighborhood")
    private String barrioDomicilioPersona;

    @Column("CANTONDOMICILIO_PERSONA")
    @JsonProperty("personResidenceCity")
    private String cantonDomicilioPersona;

    @Column("PARROQUIADOMICILIO_PERSONA")
    @JsonProperty("personResidenceParish")
    private String parroquiaDomicilioPersona;

    @Column("SECTORDOMICILIO_PERSONA")
    @JsonProperty("personResidenceSector")
    private String sectorDomicilioPersona;
}
