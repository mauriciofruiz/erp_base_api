package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table("EMPLEADO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaEmpleado {
    @Id
    @Column("ID_EMPLEADO")
    @JsonProperty("employeeId")
    private Integer idEmpleado;

    @Column("ID_POSESIONDOMICILIO")
    @JsonProperty("domicilePossessionId")
    private Integer idPosesionDomicilio;

    @Column("ID_CODIGOSECTORIAL")
    @JsonProperty("sectorialCodeId")
    private Integer idCodigoSectorial;

    @Column("ID_TIPOSANGRE")
    @JsonProperty("bloodTypeId")
    private Integer idTipoSangre;

    @Column("ID_TIPOLICENCIACONDUCIR")
    @JsonProperty("drivingLicenseTypeId")
    private Integer idTipoLicenciaConducir;

    @Column("ID_CARGO")
    @JsonProperty("positionId")
    private Integer idCargo;

    @Column("ID_SUCURSAL")
    @JsonProperty("branchId")
    private Integer idSucursal;

    @Column("ID_PERSONA")
    @JsonProperty("personId")
    private Integer idPersona;

    @Column("ID_DISCAPACIDAD")
    @JsonProperty("disabilityId")
    private Integer idDiscapacidad;

    @Column("NUMEROHIJOS_EMPLEADO")
    @JsonProperty("numberOfChildren")
    private Integer numeroHijosEmpleado;

    @Column("CELULAR_EMPLEADO")
    @JsonProperty("employeeCellphone")
    private String celularEmpleado;

    @Column("TELEFONO_EMPLEADO")
    @JsonProperty("employeePhone")
    private String telefonoEmpleado;

    @Column("CORREO_EMPLEADO")
    @JsonProperty("employeeEmail")
    private String correoEmpleado;

    @Column("FECHACADUCALICENCIA_EMPLEADO")
    @JsonProperty("driverLicenseExpirationDate")
    private LocalDate fechaCaducaLicenciaEmpleado;

    @Column("FECHAINGRESOEMPRESA_EMPLEADO")
    @JsonProperty("employeeEntryDate")
    private LocalDate fechaIngresoEmpresaEmpleado;

    @Column("FECHAREINGRESO_EMPLEADO")
    @JsonProperty("employeeReentryDate")
    private LocalDate fechaReingresoEmpleado;

    @Column("SALARIO_EMPLEADO")
    @JsonProperty("employeeSalary")
    private BigDecimal salarioEmpleado;

    @Column("HORAORDINARIA_EMPLEADO")
    @JsonProperty("ordinaryHour")
    private BigDecimal horaOrdinariaEmpleado;

    @Column("ESDISCAPACITADO_EMPLEADO")
    @JsonProperty("employeeDisabled")
    private Boolean esDiscapacitadoEmpleado;

    @Column("PORCDISCAPACIDAD_EMPLEADO")
    @JsonProperty("disabilityPercentage")
    private BigDecimal porcentajeDiscapacidadEmpleado;

    @Column("DECIMO3ACUMULADO_EMPLEADO")
    @JsonProperty("accumulatedThirteenth")
    private Boolean decimoTerceroAcumuladoEmpleado;

    @Column("DECIMO4ACUMULADO_EMPLEADO")
    @JsonProperty("accumulatedFourteenth")
    private Boolean decimoCuartoAcumuladoEmpleado;

    @Column("FRACUMULADOS_EMPLEADO")
    @JsonProperty("accumulatedVacationFunds")
    private Boolean fraccionalesAcumuladosEmpleado;

    @Column("PROVISIONVACACIONES_EMPLEADO")
    @JsonProperty("vacationProvision")
    private Boolean provisionVacacionesEmpleado;

    @Column("QUINCENA_EMPLEADO")
    @JsonProperty("fortnight")
    private Boolean quincenaEmpleado;

    @Column("VALORQUINCENA_EMPLEADO")
    @JsonProperty("fortnightValue")
    private BigDecimal valorQuincenaEmpleado;

    @Column("CORREOEMPRESA_EMPLEADO")
    @JsonProperty("companyEmail")
    private String correoEmpresaEmpleado;

    @Column("USUARIOSKYPE_EMPLEADO")
    @JsonProperty("skypeUser")
    private String usuarioSkypeEmpleado;

    @Column("TELEFONOEMPRESA_EMPLEADO")
    @JsonProperty("companyPhone")
    private String telefonoEmpresaEmpleado;

    @Column("EXTENSION_EMPLEADO")
    @JsonProperty("extension")
    private String extensionEmpleado;

    @Column("CELULAREMPRESA_EMPLEADO")
    @JsonProperty("companyCellphone")
    private String celularEmpresaEmpleado;

    @Column("USUARIOSISTEMA_EMPLEADO")
    @JsonProperty("systemUser")
    private String usuarioSistemaEmpleado;

    @Column("FECHASALIDA_EMPLEADO")
    @JsonProperty("departureDate")
    private LocalDate fechaSalidaEmpleado;

    @Column("MOTIVOSALIDA_EMPLEADO")
    @JsonProperty("departureReason")
    private String motivoSalidaEmpleado;

    @Column("ESTADO_EMPLEADO")
    @JsonProperty("employeeStatus")
    private Boolean estadoEmpleado;

    @Column("TITULO_EMPLEADO")
    @JsonProperty("employeeTitle")
    private String tituloEmpleado;

    @Column("ID_NIVELESTUDIO")
    @JsonProperty("educationLevelId")
    private Integer idNivelEstudio;

    @Column("IESSPATRONAL_EMPLEADO")
    @JsonProperty("employerIESS")
    private Boolean iessPatronalEmpleado;

    @Column("IESSPERSONAL_EMPLEADO")
    @JsonProperty("personalIESS")
    private Boolean iessPersonalEmpleado;

    @Column("MOTIVOREINGRESO_EMPLEADO")
    @JsonProperty("reentryReason")
    private String motivoReingresoEmpleado;

    @Column("IMPUESTORENTAANUAL_EMPLEADO")
    @JsonProperty("annualIncomeTax")
    private BigDecimal impuestoRentaAnualEmpleado;

    @Column("IMPUESTORENTA_EMPLEADO")
    @JsonProperty("incomeTax")
    private BigDecimal impuestoRentaEmpleado;

    @Column("IMPUESTORENTAREBAJA_EMPLEADO")
    @JsonProperty("incomeTaxRebate")
    private BigDecimal impuestoRentaRebajaEmpleado;

    @Column("PATHCEDULA_EMPLEADO")
    @JsonProperty("identificationDocCopy")
    private String pathCedulaEmpleado;

    @Column("PATHLICENCIA_EMPLEADO")
    @JsonProperty("driveLicenceEmployee")
    private String pathLicenciaEmpleado;

    @Column("PATHCV_EMPLEADO")
    @JsonProperty("cvEmployee")
    private String pathCvEmpleado;
}
