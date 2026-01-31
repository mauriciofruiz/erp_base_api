package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table("CLIENTE")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class TbaCliente extends BaseEntity {
    @Id
    @Column("ID_CLIENTE")
    @JsonProperty("clientId")
    private Integer idCliente;

    @Column("ID_CATEGORIACLIENTE")
    @JsonProperty("clientCategoryId")
    private Integer idCategoriaCliente;

    @Column("ID_ORIGENINGRESO")
    @JsonProperty("incomeOriginId")
    private Integer idOrigenIngreso;

    @Column("ID_TIPOIDFISCAL")
    @JsonProperty("fiscalIdentificationTypeId")
    private String idTipoIdFiscal;

    @Column("ID_PERSONA")
    @JsonProperty("personId")
    private Integer idPersona;

    @Column("ID_CUENTA")
    @JsonProperty("accountId")
    private Integer idCuenta;

    @Column("NOMBRECOMERCIAL_CLIENTE")
    @JsonProperty("clientCommercialName")
    private String nombreComercialCliente;

    @Column("DIRECCION2_CLIENTE")
    @JsonProperty("clientAddress2")
    private String direccion2Cliente;

    @Column("CORREO2_CLIENTE")
    @JsonProperty("clientEmail2")
    private String correo2Cliente;

    @Column("CELULAR2_CLIENTE")
    @JsonProperty("clientCellphone2")
    private String celular2Cliente;

    @Column("TELEFONO2_CLIENTE")
    @JsonProperty("clientPhone2")
    private String telefono2Cliente;

    @Column("SALDOAFAVOR_CLIENTE")
    @JsonProperty("clientBalance")
    private BigDecimal saldoAFavorCliente;

    @Column("CONTACTO_CLIENTE")
    @JsonProperty("clientContact")
    private String contactoCliente;

    @Column("CARGOCONTACTO_CLIENTE")
    @JsonProperty("clientContactPosition")
    private String cargoContactoCliente;

    @Column("TELEFONOCONTACTO_CLIENTE")
    @JsonProperty("clientContactPhone")
    private String telefonoContactoCliente;

    @Column("CORREOCONTACTO_CLIENTE")
    @JsonProperty("clientContactEmail")
    private String correoContactoCliente;

    @Column("CELULARCONTACTO_CLIENTE")
    @JsonProperty("clientContactCellphone")
    private String celularContactoCliente;

    @Column("DESCUENTOESPECIAL_CLIENTE")
    @JsonProperty("specialDiscount")
    private BigDecimal descuentoEspecialCliente;

    @Column("FECHACADUCIDADDESC_CLIENTE")
    @JsonProperty("discountExpirationDate")
    private LocalDate fechaCaducidadDescCliente;

    @Column("OBSDESC_CLIENTE")
    @JsonProperty("discountObservation")
    private String obsDescCliente;

    @Column("alerta_general")
    @JsonProperty("generalAlert")
    private String alertaGeneral;

    @Column("alerta_facturacion")
    @JsonProperty("billingAlert")
    private String alertaFacturacion;

    @Column("alerta_cobro")
    @JsonProperty("collectionAlert")
    private String alertaCobro;

    @Column("CODDESBLOQUEO_CLIENTE")
    @JsonProperty("clientUnlockCode")
    private String codDesbloqueoCliente;

    @Column("NUMCAMBIO_CLIENTE")
    @JsonProperty("clientChangeNumber")
    private Integer numCambioCliente;

    @Column("MOTIVOINACTIVA_CLIENTE")
    @JsonProperty("clientInactiveReason")
    private String motivoInactivaCliente;

    @Column("ESTADO_CLIENTE")
    @JsonProperty("clientStatus")
    private Boolean estadoCliente;

    @Column("id_usuario_ventas")
    @JsonProperty("salesUserId")
    private Integer idUsuarioVentas;

    @Column("FACCONTADO_CLIENTE")
    @JsonProperty("clientCashInvoice")
    private Boolean facContadoCliente;

    @Column("CATEGORIAESPECIAL_CLIENTE")
    @JsonProperty("specialCategory")
    private String categoriaEspecialCliente;

    @Column("POLITICA_CLIENTE")
    @JsonProperty("clientPolicy")
    private String politicaCliente;

    @Column("descuento_cliente")
    @JsonProperty("clientDiscount")
    private BigDecimal descuentoCliente;

    @Column("id_retencioniva")
    @JsonProperty("ivaRetentionId")
    private Integer idRetencionIva;

    @Column("id_retencionfuente")
    @JsonProperty("sourceRetentionId")
    private Integer idRetencionFuente;

    @Column("cliente_placa")
    @JsonProperty("plate")
    private String placa;
}
