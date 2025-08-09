package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaClienteDto {

    @NotNull(message = "El id del cliente no puede ser nulo")
    @JsonProperty("clientCategoryId")
    private Integer idCategoriaCliente;

    @NotNull(message = "El id del origen ingreso no puede ser nulo")
    @JsonProperty("incomeOriginId")
    private Integer idOrigenIngreso;

    @NotBlank(message = "El id del tipo de identificacion fiscal no puede ser nulo")
    @JsonProperty("fiscalIdentificationTypeId")
    private String idTipoIdFiscal;

    @NotBlank(message = "El id de la persona no puede ser nulo")
    @JsonProperty("personId")
    private Integer idPersona;

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

    @JsonProperty("plate")
    private String placa;
}
