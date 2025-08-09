package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProveedorPatchDto {

    @JsonProperty("taxIdentificationTypeId")
    private String idTipoIdFiscal;

    @JsonProperty("ivaRetentionId")
    private Integer idRetencionIva;

    @JsonProperty("personId")
    private Integer idPersona;

    @JsonProperty("personName")
    private String nombrePersona;

    @JsonProperty("bankCatalogId")
    private Integer idBancoCatalogo;

    @JsonProperty("incomeRetentionId")
    private Integer idRetencionFuente;

    @JsonProperty("providerTypeId")
    private Integer idTipoProv;

    @JsonProperty("bankAccountTypeId")
    private Integer idTipoCuentaBancaria;

    @JsonProperty("paymentConditionId")
    private Integer idCondicionPago;

    @JsonProperty("paymentCollectionFormId")
    private String idFormaPagoCobro;

    @JsonProperty("providerTradeName")
    private String nombreComercialProveedor;

    @JsonProperty("providerAddress2")
    private String direccion2Proveedor;

    @JsonProperty("providerEmail2")
    private String correo2Proveedor;

    @JsonProperty("providerCellphone2")
    private String celular2Proveedor;

    @JsonProperty("providerPhone2")
    private String telefono2Proveedor;

    @JsonProperty("providerWebsite")
    private String sitioWebProveedor;

    @JsonProperty("providerContact")
    private String contactoProveedor;

    @JsonProperty("providerContactPosition")
    private String cargoContactoProveedor;

    @JsonProperty("providerContactPhone")
    private String telefonoContactoProveedor;

    @JsonProperty("providerContactEmail")
    private String correoContactoProveedor;

    @JsonProperty("providerContactCellphone")
    private String celularContactoProveedor;

    @JsonProperty("providerCredit")
    private Boolean creditoProveedor;

    @JsonProperty("providerCreditAmount")
    private BigDecimal montoCreditoProveedor;

    @JsonProperty("providerCreditDueDate")
    private LocalDate fechaVencimientoCreditoProveedor;

    @JsonProperty("providerCreditDays")
    private Integer diasCreditoProveedor;

    @JsonProperty("providerGuaranteeAmount")
    private BigDecimal montoGarantiaProveedor;

    @JsonProperty("providerBankAccountNumber")
    private String numCuentaBancariaProveedor;

    @JsonProperty("providerPaymentReference")
    private String referenciaPagoProveedor;

    @JsonProperty("providerAdvancePaymentBalance")
    private BigDecimal saldoAnticipoProveedor;

    @JsonProperty("providerCheckName")
    private String nombreChequeProveedor;

    @JsonProperty("providerSpecialContribution")
    private String contribEspecialProveedor;

    @JsonProperty("providerTransportation")
    private Boolean esTransporteProveedor;

    @JsonProperty("providerRegistrationDate")
    private ZonedDateTime fechaRegistroProveedor;

    @JsonProperty("providerStatus")
    private Boolean estadoProveedor;

    @JsonProperty("paymentAccountNumber")
    private String numeroCuentaPago;

    @JsonProperty("fiscalIdentificationPaymentId")
    private Integer idIdentificacionFiscalPago;

    @JsonProperty("providerPassiveAccount")
    private Integer cuentaPasivaProveedor;

    @JsonProperty("providerExpenseAccount")
    private Integer cuentaGastoProveedor;
}
