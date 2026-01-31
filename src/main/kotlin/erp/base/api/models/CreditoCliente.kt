package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table("CREDITOCLIENTE")
data class CreditoCliente(
    @Id
    @Column("ID_CREDITOCLIENTE")
    @JsonProperty("creditClientId")
    var idCreditoCliente: Int? = null,

    @Column("ID_CLIENTE")
    @JsonProperty("clientId")
    var idCliente: Int? = null,

    @Column("CUPOAUTORIZADO_CREDITOCLIENTE")
    @JsonProperty("authorizedCreditLimit")
    var cupoAutorizado: BigDecimal? = null,

    @Column("CUPODISPONIBLE_CREDITOCLIENTE")
    @JsonProperty("availableCreditLimit")
    var cupoDisponible: BigDecimal? = null,

    @Column("CUPOUTILIZADO_CREDITOCLIENTE")
    @JsonProperty("usedCreditLimit")
    var cupoUtilizado: BigDecimal? = null,

    @Column("MONTOGARANTIA_CREDITOCLIENTE")
    @JsonProperty("guaranteeAmount")
    var montoGarantia: BigDecimal? = null,

    @Column("FECHA_GARANTIA")
    @JsonProperty("guaranteeDate")
    var fechaGarantia: LocalDateTime? = null,

    @Column("DIAS_CREDITOCLIENTE")
    @JsonProperty("creditDays")
    var diasCredito: Int? = null,

    @Column("OBS_CREDITOCLIENTE")
    @JsonProperty("creditObservation")
    var observacion: String? = null,

    @Column("SOBRECUPO_CREDITOCLIENTE")
    @JsonProperty("overlimit")
    var sobreCupo: BigDecimal? = null,

    @Column("FECHAVCTOSOBRECUPO_CREDITOCLIENTE")
    @JsonProperty("overlimitExpirationDate")
    var fechaVencimientoSobreCupo: LocalDateTime? = null,

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
