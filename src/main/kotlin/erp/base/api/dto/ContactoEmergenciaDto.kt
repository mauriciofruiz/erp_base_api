package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ContactoEmergenciaDto(
    @JsonProperty("contactEmergencyId")
    val idContactoemergencia: Int? = null,
    @JsonProperty("kinId")
    val idParentesco: Int? = null,
    @JsonProperty("nameContactEmergency")
    val nombreContactoemergencia: String? = null,
    @JsonProperty("name")
    val nombreParentesco: String? = null,
    @JsonProperty("cellphoneEmergencyContact")
    val celularContactoemergencia: String? = null,
    @JsonProperty("telephoneEmergencyContact")
    val telefonoContactoemergencia: String? = null,
)
