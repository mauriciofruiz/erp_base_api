package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.time.LocalDateTime

@Table("PERIODO")
data class Periodo(
    @Id
    @Column("ID_PERIODO")
    @JsonProperty("periodId")
    val idPeriodo: Int? = null,

    @Column("FECHACIERRE_PERIODO")
    @JsonProperty("closingPeriodDate")
    val fechaCierrePeriodo: LocalDate? = null,

    @Column("FECHAANTERIOR_PERIODO")
    @JsonProperty("previousPeriodDate")
    val fechaAnteriorPeriodo: LocalDate? = null,

    @Column("id_modulo")
    @JsonProperty("moduleId")
    val idModulo: Int? = null,

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
