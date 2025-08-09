package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaEmpleadoDto {

    @JsonProperty("personId")
    private Integer idPersona;

    @JsonProperty("employeeId")
    private Integer idEmpleado;

    @JsonProperty("positionId")
    private Integer idCargo;

    @JsonProperty("branchId")
    private Integer idSucursal;

    @JsonProperty("personIdentification")
    private String identificacionPersona;

    @JsonProperty("personName")
    private String nombrePersona;

    @JsonProperty("personBirthDate")
    private LocalDate fechanacimientoPersona;

    @JsonProperty("bloodTypeId")
    private Integer idTiposangre;

    @JsonProperty("bloodTypeName")
    private String nombreTiposangre;

    @JsonProperty("educationLevelId")
    private Integer idNivelestudio;

    @JsonProperty("educationLevelName")
    private String nombreNivelestudio;

    @JsonProperty("cityName")
    private String nombreCiudad;

    @JsonProperty("parishId")
    private Integer idParroquia;

    @JsonProperty("parishName")
    private String nombreParroquia;

    @JsonProperty("provinceName")
    private String nombreProvincia;

    @JsonProperty("maritalStatusId")
    private Integer idEstadocivil;

    @JsonProperty("maritalStatusName")
    private String nombreEstadocivil;

    @JsonProperty("genderId")
    private Integer idGenero;

    @JsonProperty("genderName")
    private String nombreGenero;

    @JsonProperty("employeeTitle")
    private String tituloEmpleado;

    @JsonProperty("personAddress")
    private String direccionPersona;

    @JsonProperty("personResidenceSector")
    private String sectordomicilioPersona;

    @JsonProperty("personEmail")
    private String correoPersona;

    @JsonProperty("personCellphone")
    private String celularPersona;

    @JsonProperty("employeePhone")
    private String telefonoEmpleado;

    @JsonProperty("employeeCellphone")
    private String celularEmpleado;

    @JsonProperty("drivingLicenseTypeId")
    private Integer idTipolicenciaconducir;

    @JsonProperty("drivingLicenseTypeName")
    private String nombreTipolicenciaconducir;

    @JsonProperty("drivingLicenseTypeDescription")
    private String descripcionTipolicenciaconducir;

    @JsonProperty("driverLicenseExpirationDate")
    private LocalDate fechacaducalicenciaEmpleado;


    @JsonProperty("employeeDisabled")
    private Boolean esdiscapacitadoEmpleado;

    @JsonProperty("disabilityId")
    private Integer idDiscapacidad;

    @JsonProperty("disabilityName")
    private String nombreDiscapacidad;

    @JsonProperty("disabilityPercentage")
    private BigDecimal porcdiscapacidadEmpleado;

    @JsonProperty("familyResponsibilities")
    private List<RrhCargasFamiliaresDto> rrhCargasFamiliaresDtos;

    @JsonProperty("emergencyContact")
    private List<RrhContactoEmergenciaDto> rrhContactoEmergenciaDtos;

    @JsonProperty("bankAccount")
    private List<TbaCuentaBancariaDto> tbaCuentaBancariaDtos;

    @JsonProperty("identificationDocCopy")
    private String pathCedulaEmpleado;

    @JsonProperty("driveLicenceEmployee")
    private String pathLicenciaEmpleado;

    @JsonProperty("cvEmployee")
    private String pathCvEmpleado;

    @JsonProperty("img")
    private String imgUsr;

}
