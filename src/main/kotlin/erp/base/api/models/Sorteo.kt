package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table("SORTEO")
data class Sorteo(
    @Id
    @Column("ID_SORTEO")
    @JsonProperty("lotteryId")
    val idSorteo: Int,
    @Column("ANULADO_SORTEO")
    @JsonProperty("cancelled")
    val anuladoSorteo: Boolean? = null,
    @Column("DESCRIPCION_SORTEO")
    @JsonProperty("description")
    val descripcionSorteo: String? = null,
    @Column("FECHAFIN_SORTEO")
    @JsonProperty("endDate")
    val fechaFinSorteo: LocalDateTime? = null,
    @Column("FECHAINICIO_SORTEO")
    @JsonProperty("startDate")
    val fechaInicioSorteo: LocalDateTime? = null,
    @Column("MONTOPARTICIPANTE_SORTEO")
    @JsonProperty("participantAmount")
    val montoParticipanteSorteo: BigDecimal? = null,
    @Column("NOMBRE_SORTEO")
    @JsonProperty("name")
    val nombreSorteo: String? = null,
    @Column("ID_CUPON_SORTEO")
    @JsonProperty("couponId")
    val idCuponSorteo: Int? = null,
)
