package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Table("CLIENTE")
data class Cliente(
    @Id
    @Column("ID_CLIENTE")
    @JsonProperty("clientId")
    var idCliente: Int? = null,

    @Column("ID_CATEGORIACLIENTE")
    @JsonProperty("clientCategoryId")
    var idCategoriaCliente: Int? = null,

    @Column("ID_ORIGENINGRESO")
    @JsonProperty("incomeOriginId")
    var idOrigenIngreso: Int? = null,

    @Column("ID_TIPOIDFISCAL")
    @JsonProperty("fiscalIdentificationTypeId")
    var idTipoIdFiscal: String? = null,

    @Column("ID_PERSONA")
    @JsonProperty("personId")
    var idPersona: Int? = null,

    @Column("ID_CUENTA")
    @JsonProperty("accountId")
    var idCuenta: Int? = null,

    @Column("NOMBRECOMERCIAL_CLIENTE")
    @JsonProperty("clientCommercialName")
    var nombreComercialCliente: String? = null,

    @Column("DIRECCION2_CLIENTE")
    @JsonProperty("clientAddress2")
    var direccion2Cliente: String? = null,

    @Column("CORREO2_CLIENTE")
    @JsonProperty("clientEmail2")
    var correo2Cliente: String? = null,

    @Column("CELULAR2_CLIENTE")
    @JsonProperty("clientCellphone2")
    var celular2Cliente: String? = null,

    @Column("TELEFONO2_CLIENTE")
    @JsonProperty("clientPhone2")
    var telefono2Cliente: String? = null,

    @Column("SALDOAFAVOR_CLIENTE")
    @JsonProperty("clientBalance")
    var saldoAFavorCliente: BigDecimal? = null,

    @Column("CONTACTO_CLIENTE")
    @JsonProperty("clientContact")
    var contactoCliente: String? = null,

    @Column("CARGOCONTACTO_CLIENTE")
    @JsonProperty("clientContactPosition")
    var cargoContactoCliente: String? = null,

    @Column("TELEFONOCONTACTO_CLIENTE")
    @JsonProperty("clientContactPhone")
    var telefonoContactoCliente: String? = null,

    @Column("CORREOCONTACTO_CLIENTE")
    @JsonProperty("clientContactEmail")
    var correoContactoCliente: String? = null,

    @Column("CELULARCONTACTO_CLIENTE")
    @JsonProperty("clientContactCellphone")
    var celularContactoCliente: String? = null,

    @Column("DESCUENTOESPECIAL_CLIENTE")
    @JsonProperty("specialDiscount")
    var descuentoEspecialCliente: BigDecimal? = null,

    @Column("FECHACADUCIDADDESC_CLIENTE")
    @JsonProperty("discountExpirationDate")
    var fechaCaducidadDescCliente: LocalDate? = null,

    @Column("OBSDESC_CLIENTE")
    @JsonProperty("discountObservation")
    var obsDescCliente: String? = null,

    @Column("alerta_general")
    @JsonProperty("generalAlert")
    var alertaGeneral: String? = null,

    @Column("alerta_facturacion")
    @JsonProperty("billingAlert")
    var alertaFacturacion: String? = null,

    @Column("alerta_cobro")
    @JsonProperty("collectionAlert")
    var alertaCobro: String? = null,

    @Column("CODDESBLOQUEO_CLIENTE")
    @JsonProperty("clientUnlockCode")
    var codDesbloqueoCliente: String? = null,

    @Column("NUMCAMBIO_CLIENTE")
    @JsonProperty("clientChangeNumber")
    var numCambioCliente: Int? = null,

    @Column("MOTIVOINACTIVA_CLIENTE")
    @JsonProperty("clientInactiveReason")
    var motivoInactivaCliente: String? = null,

    @Column("ESTADO_CLIENTE")
    @JsonProperty("clientStatus")
    var estadoCliente: Boolean? = null,

    @Column("id_usuario_ventas")
    @JsonProperty("salesUserId")
    var idUsuarioVentas: Int? = null,

    @Column("FACCONTADO_CLIENTE")
    @JsonProperty("clientCashInvoice")
    var facContadoCliente: Boolean? = null,

    @Column("CATEGORIAESPECIAL_CLIENTE")
    @JsonProperty("specialCategory")
    var categoriaEspecialCliente: String? = null,

    @Column("POLITICA_CLIENTE")
    @JsonProperty("clientPolicy")
    var politicaCliente: String? = null,

    @Column("descuento_cliente")
    @JsonProperty("clientDiscount")
    var descuentoCliente: BigDecimal? = null,

    @Column("id_retencioniva")
    @JsonProperty("ivaRetentionId")
    var idRetencionIva: Int? = null,

    @Column("id_retencionfuente")
    @JsonProperty("sourceRetentionId")
    var idRetencionFuente: Int? = null,

    @Column("cliente_placa")
    @JsonProperty("plate")
    var placa: String? = null,

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
