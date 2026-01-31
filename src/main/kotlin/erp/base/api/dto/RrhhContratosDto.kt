package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDate

data class RrhhContratosDto(
    @JsonProperty("contractId")
    val idContrato: Int? = null,
    @JsonProperty("contractTypeId")
    val idTipocontrato: Int? = null,
    @JsonProperty("contractName")
    val nombreTipocontrato: String? = null,
    @JsonProperty("contractModalityId")
    val idModalidadcontrato: Int? = null,
    @JsonProperty("nameContractType")
    val nombreModalidadcontrato: String? = null,
    @JsonProperty("startDateContract")
    val fechainicioContrato: LocalDate? = null,
    @JsonProperty("endDateContract")
    val fechafinContrato: LocalDate? = null,
    @JsonProperty("stateContract")
    val estadoContrato: Boolean? = null,
    @JsonProperty("fileContract")
    val archivoContrato: String? = null,
    @JsonProperty("contractHours")
    val nrohorasContrato: BigDecimal? = null,
)
