package erp.base.api.models.rrh

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("TIPOLICENCIACONDUCIR")
data class TipoLicenciaConducir(
    @Id
    @Column("ID_TIPOLICENCIACONDUCIR")
    @JsonProperty("drivingLicenseTypeId")
    val idTipoLicenciaConducir: Int,
    @Column("NOMBRE_TIPOLICENCIACONDUCIR")
    @JsonProperty("drivingLicenseTypeName")
    val nombreTipoLicenciaConducir: String? = null,
    @Column("DESCRIPCION_TIPOLICENCIACONDUCIR")
    @JsonProperty("drivingLicenseTypeDescription")
    val descripcionTipoLicenciaConducir: String? = null,
)
