package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicon.tba.models.BaseEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class TbaPersonaDto extends BaseEntity {

    @JsonProperty("personId")
    private Integer idPersona;

    @JsonProperty("contributorTypeId")
    private String idTipoContribuyente;

    @JsonProperty("countryId")
    private Integer idPais;

    @JsonProperty("parishId")
    private Integer idParroquia;

    @JsonProperty("maritalStatusId")
    private Integer idEstadoCivil;

    @JsonProperty("genderId")
    private Integer idGenero;

    @JsonProperty("taxIdentificationId")
    private Integer idIdentificacionFiscal;

    @Pattern(regexp = "\\d{10,13}", message = "La identificación de la persona debe tener entre 10 y 13 dígitos")
    @JsonProperty("personIdentification")
    private String identificacionPersona;

    @JsonProperty("personName")
    private String nombrePersona;

    @JsonProperty("personBirthDate")
    private LocalDate fechaNacimientoPersona;

    @NotNull(message = "La dirección de la persona no puede estar vacía")
    @Size(min = 5, max = 500, message = "La dirección de la persona debe tener entre 5 y 500 caracteres")
    @JsonProperty("personAddress")
    private String direccionPersona;

    @NotNull(message = "El celular de la persona no puede estar vacío")
    @JsonProperty("personCellphone")
    private String celularPersona;

    @JsonProperty("personPhone")
    private String telefonoPersona;

    @JsonProperty("personEmail")
    private String correoPersona;

    @JsonProperty("isClient")
    private Boolean esClientePersona;

    @JsonProperty("isEmployee")
    private Boolean esEmpleadoPersona;

    @JsonProperty("isProvider")
    private Boolean esProveedorPersona;

    @JsonProperty("personRegistrationDate")
    private LocalDateTime fechaRegistroPersona;

    @JsonProperty("personResidenceNeighborhood")
    private String barrioDomicilioPersona;

    @JsonProperty("personResidenceCity")
    private String cantonDomicilioPersona;

    @JsonProperty("personResidenceParish")
    private String parroquiaDomicilioPersona;

    @JsonProperty("personResidenceSector")
    private String sectorDomicilioPersona;
}
