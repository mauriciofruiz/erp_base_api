package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("CASOSVERIFICACION")
data class CasosVerificacion(
    @Id
    @Column("ID_CASOSVERIFICACION")
    @JsonProperty("verificationCaseId")
    val idCasosVerificacion: Int,
    @Column("NOMBRE_CASOSVERIFICACION")
    @JsonProperty("verificationCaseName")
    val nombreCasosVerificacion: String? = null,
)
