package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table("APERTURACAJA")
data class AperturaCaja(
    @Id
    @Column("ID_APERTURACAJA")
    @JsonProperty("cashOpeningId")
    val idAperturaCaja: Int,
    @Column("ID_CAJA")
    @JsonProperty("cashboxId")
    val idCaja: Int? = null,
    @Column("ID_USR")
    @JsonProperty("userId")
    val idUsuario: Int? = null,
    @Column("ID_ASIENTO")
    @JsonProperty("accountingEntryId")
    val idAsiento: Int? = null,
    @Column("VALORINICIAL_APERTURACAJA")
    @JsonProperty("initialValue")
    val valorInicial: BigDecimal? = null,
    @Column("VALORFINAL_APERTURACAJA")
    @JsonProperty("finalValue")
    val valorFinal: BigDecimal? = null,
    @Column("FECHAAPERTURA_APERTURACAJA")
    @JsonProperty("openingDate")
    val fechaApertura: LocalDateTime? = null,
    @Column("FECHACIERRE_APERTURACAJA")
    @JsonProperty("closingDate")
    val fechaCierre: LocalDateTime? = null,
    @Column("ESTADO_APERTURACAJA")
    @JsonProperty("state")
    val estado: Boolean? = null,
    @Column("ESMANUAL_APERTURACAJA")
    @JsonProperty("manual")
    val esManual: Boolean? = null,
)
