package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class CargasFamiliaresDto(
    @JsonProperty("familyBurdenId")
    var idCargafml: Int? = null,
    @JsonProperty("idKind")
    var idParentesco: Int? = null,
    @JsonProperty("relativeName")
    var nombreParentesco: String? = null,
    @JsonProperty("identification")
    var cedulaCargafml: String? = null,
    @JsonProperty("names")
    var nombreCargafml: String? = null,
    @JsonProperty("personBirthDate")
    var fechanacimientoCargafml: LocalDate? = null,
    @JsonProperty("especialCapacity")
    var capacidadespecialCargafml: Boolean? = null,
    @JsonProperty("enableBurdenFml")
    var activaCargafml: Boolean? = null,
    @JsonProperty("age")
    var edad: Int? = null,
)
