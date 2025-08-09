package com.sicon.tba.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicon.tba.dto.ExcepcionesClienteDescDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PersonaClienteCreditoDto {
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

    @JsonProperty("clientCategoryId")
    private Integer idCategoriaCliente;

    @NotNull(message = "El id del origen ingreso no puede ser nulo")
    @JsonProperty("incomeOriginId")
    private Integer idOrigenIngreso;

    @NotBlank(message = "El id del tipo de identificacion fiscal no puede ser nulo")
    @JsonProperty("fiscalIdentificationTypeId")
    private String idTipoIdFiscal;

    @NotNull(message = "El id de la cuenta no puede ser nulo")
    @JsonProperty("accountId")
    private Integer idCuenta;

    @NotBlank(message = "El nombre comercial del cliente no puede estar vacio")
    @JsonProperty("clientCommercialName")
    private String nombreComercialCliente;

    @JsonProperty("clientAddress2")
    private String direccion2Cliente;

    @JsonProperty("clientEmail2")
    private String correo2Cliente;

    @JsonProperty("clientCellphone2")
    private String celular2Cliente;

    @JsonProperty("clientPhone2")
    private String telefono2Cliente;

    @JsonProperty("clientBalance")
    private BigDecimal saldoAFavorCliente;

    @JsonProperty("clientContact")
    private String contactoCliente;

    @JsonProperty("clientContactPosition")
    private String cargoContactoCliente;

    @JsonProperty("clientContactPhone")
    private String telefonoContactoCliente;

    @JsonProperty("clientContactEmail")
    private String correoContactoCliente;

    @JsonProperty("clientContactCellphone")
    private String celularContactoCliente;

    @JsonProperty("specialDiscount")
    private BigDecimal descuentoEspecialCliente;

    @JsonProperty("discountExpirationDate")
    private LocalDate fechaCaducidadDescCliente;

    @JsonProperty("discountObservation")
    private String obsDescCliente;

    @JsonProperty("generalAlert")
    private String alertaGeneral;

    @JsonProperty("billingAlert")
    private String alertaFacturacion;

    @JsonProperty("collectionAlert")
    private String alertaCobro;

    @JsonProperty("clientUnlockCode")
    private String codDesbloqueoCliente;

    @JsonProperty("clientChangeNumber")
    private Integer numCambioCliente;

    @JsonProperty("clientInactiveReason")
    private String motivoInactivaCliente;

    @JsonProperty("clientStatus")
    private Boolean estadoCliente;

    @JsonProperty("salesUserId")
    private Integer idUsuarioVentas;

    @JsonProperty("clientCashInvoice")
    private Boolean facContadoCliente;

    @JsonProperty("specialCategory")
    private String categoriaEspecialCliente;

    @JsonProperty("clientPolicy")
    private String politicaCliente;

    @JsonProperty("clientDiscount")
    private BigDecimal descuentoCliente;

    @JsonProperty("ivaRetentionId")
    private Integer idRetencionIva;

    @JsonProperty("sourceRetentionId")
    private Integer idRetencionFuente;

    @JsonProperty("creditClientId")
    private Integer idCreditoCliente;

    @JsonProperty("clientId")
    private Integer idCliente;

    @JsonProperty("authorizedCreditLimit")
    private BigDecimal cupoAutorizado;

    @JsonProperty("availableCreditLimit")
    private BigDecimal cupoDisponible;

    @JsonProperty("usedCreditLimit")
    private BigDecimal cupoUtilizado;

    @JsonProperty("guaranteeAmount")
    private BigDecimal montoGarantia;

    @JsonProperty("guaranteeDate")
    private LocalDateTime fechaGarantia;

    @JsonProperty("creditDays")
    private Integer diasCredito;

    @JsonProperty("creditObservation")
    private String observacion;

    @JsonProperty("overlimit")
    private BigDecimal sobreCupo;

    @JsonProperty("overlimitExpirationDate")
    private LocalDateTime fechaVencimientoSobreCupo;

    @JsonProperty("registrationDate")
    private LocalDateTime fechaCreacion;

    @JsonProperty("modificationDate")
    private LocalDateTime fechaModificacion;

    @JsonProperty("cityId")
    private Integer idCiudad;

    @JsonProperty("provinceId")
    private String idProvincia;

    @JsonProperty("plate")
    private String placa;

    @JsonProperty("exceptions")
    private List<ExcepcionesClienteDescDto> excepciones;

    @JsonProperty("statementAccount")
    private EstadoCuentoPagarDto detalleEstadoCuentaCobrarDtoList;

}