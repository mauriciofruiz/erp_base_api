package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table("TARIFAIVA")
data class TarifaIva(
    @Id
    @Column("ID_TARIFAIVA")
    @JsonProperty("taxRateId")
    val idTarifaIva: Int? = null,

    @Column("PORC_TARIFAIVA")
    @JsonProperty("taxRatePercentage")
    val porcentajeTarifaIva: BigDecimal? = null,

    @Column("COD_TARIFAIVA")
    @JsonProperty("taxRateCode")
    val codigoTarifaIva: String? = null,

    @Column("descripcion_iva")
    @JsonProperty("taxDescription")
    val descripcionIva: String? = null,

    @Column("id_cuenta_venta")
    @JsonProperty("saleAccountId")
    val idCuentaVenta: Int? = null,

    @Column("id_cuenta_compra")
    @JsonProperty("purchaseAccountId")
    val idCuentaCompra: Int? = null,

    @Column("estado_tarifaiva")
    @JsonProperty("taxRateState")
    val estadoTarifaIva: Boolean? = null,

    @Column("id_usuario_creacion")
    @JsonProperty("creationUserId")
    val idUsuarioCreacion: Int? = null,

    @Column("id_usuario_modificacion")
    @JsonProperty("modificationUserId")
    val idUsuarioModificacion: Int? = null,

    @Column("fecha_creacion")
    @JsonProperty("registrationDate")
    val fechaCreacion: LocalDateTime? = null,

    @Column("fecha_modificacion")
    @JsonProperty("modificationDate")
    val fechaModificacion: LocalDateTime? = null,
)
