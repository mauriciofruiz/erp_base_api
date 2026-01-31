package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Table("PROVEEDOR")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Proveedor extends BaseEntity {
    @Id
    @Column("id_proveedor")
    @JsonProperty("providerId")
    private Integer idProveedor;

    @Column("id_tipoidfiscal")
    @JsonProperty("fiscalTypeId")
    private String idTipoIdFiscal;

    @Column("id_retencioniva")
    @JsonProperty("vatRetentionId")
    private Integer idRetencionIva;

    @Column("id_persona")
    @JsonProperty("personId")
    private Integer idPersona;

    @Column("id_bancocatalogo")
    @JsonProperty("bankCatalogId")
    private Integer idBancoCatalogo;

    @Column("id_retencionfuente")
    @JsonProperty("sourceRetentionId")
    private Integer idRetencionFuente;

    @Column("id_tipoprov")
    @JsonProperty("providerTypeId")
    private Integer idTipoProveedor;

    @Column("id_tipocuentabancaria")
    @JsonProperty("bankAccountTypeId")
    private Integer idTipoCuentaBancaria;

    @Column("id_condicionpago")
    @JsonProperty("paymentConditionId")
    private Integer idCondicionPago;

    @Column("id_formapagocobro")
    @JsonProperty("paymentMethodId")
    private String idFormaPagoCobro;

    @Column("nombrecomercial_proveedor")
    @JsonProperty("providerCommercialName")
    private String nombreComercialProveedor;

    @Column("direccion2_proveedor")
    @JsonProperty("providerAddress2")
    private String direccion2Proveedor;

    @Column("correo2_proveedor")
    @JsonProperty("providerEmail2")
    private String correo2Proveedor;

    @Column("celular2_proveedor")
    @JsonProperty("providerCellphone2")
    private String celular2Proveedor;

    @Column("telefono2_proveedor")
    @JsonProperty("providerPhone2")
    private String telefono2Proveedor;

    @Column("sitioweb_proveedor")
    @JsonProperty("providerWebsite")
    private String sitioWebProveedor;

    @Column("contacto_proveedor")
    @JsonProperty("providerContact")
    private String contactoProveedor;

    @Column("cargocontacto_proveedor")
    @JsonProperty("providerContactPosition")
    private String cargoContactoProveedor;

    @Column("telefonocontacto_proveedor")
    @JsonProperty("providerContactPhone")
    private String telefonoContactoProveedor;

    @Column("correocontacto_proveedor")
    @JsonProperty("providerContactEmail")
    private String correoContactoProveedor;

    @Column("celularcontacto_proveedor")
    @JsonProperty("providerContactCellphone")
    private String celularContactoProveedor;

    @Column("credito_proveedor")
    @JsonProperty("isCredit")
    private Boolean creditoProveedor;

    @Column("montocredito_proveedor")
    @JsonProperty("creditAmount")
    private BigDecimal montoCreditoProveedor;

    @Column("fechavencimientocredito_proveedor")
    @JsonProperty("creditExpirationDate")
    private LocalDate fechaVencimientoCreditoProveedor;

    @Column("diascredito_proveedor")
    @JsonProperty("creditDays")
    private Integer diasCreditoProveedor;

    @Column("montogarantia_proveedor")
    @JsonProperty("warrantyAmount")
    private BigDecimal montoGarantiaProveedor;

    @Column("numcuentabancaria_proveedor")
    @JsonProperty("providerBankAccountNumber")
    private String numeroCuentaBancariaProveedor;

    @Column("referenciapago_proveedor")
    @JsonProperty("paymentReference")
    private String referenciaPagoProveedor;

    @Column("saldoanticipo_proveedor")
    @JsonProperty("advancePaymentBalance")
    private BigDecimal saldoAnticipoProveedor;

    @Column("nombrecheque_proveedor")
    @JsonProperty("providerCheckName")
    private String nombreChequeProveedor;

    @Column("contribespecial_proveedor")
    @JsonProperty("specialContributor")
    private String contribucionEspecialProveedor;

    @Column("estransporte_proveedor")
    @JsonProperty("isTransporter")
    private Boolean esTransporteProveedor;

    @Column("estado_proveedor")
    @JsonProperty("providerStatus")
    private Boolean estadoProveedor;

    @Column("numero_cuenta_pago")
    @JsonProperty("paymentAccountNumber")
    private String numeroCuentaPago;

    @Column("id_identificacionfiscal_pago")
    @JsonProperty("fiscalIdentificationPaymentId")
    private Integer idIdentificacionFiscalPago;

    @Column("cuentapasiva_proveedor")
    @JsonProperty("passiveAccount")
    private Integer cuentaPasivaProveedor;

    @Column("cuentagasto_proveedor")
    @JsonProperty("expenseAccount")
    private Integer cuentaGastoProveedor;

}
