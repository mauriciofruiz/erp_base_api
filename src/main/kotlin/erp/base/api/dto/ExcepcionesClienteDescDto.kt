package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.ZonedDateTime

data class ExcepcionesClienteDescDto(
    @JsonProperty("exceptionId")
    var idExcepcion: Int? = null,
    @JsonProperty("clientId")
    var idCliente: Long? = null,
    @JsonProperty("exceptionTypeId")
    var idTipoExcepcion: Int? = null,
    @JsonProperty("parameterId")
    var idParametro: Int? = null,
    @JsonProperty("parameterValue")
    var valorParametro: String? = null,
    @JsonProperty("discountPortion")
    var porcionDescuento: BigDecimal? = null,
    @JsonProperty("startDate")
    var fechaInicio: ZonedDateTime? = null,
    @JsonProperty("endDate")
    var fechaFin: ZonedDateTime? = null,
    @JsonProperty("status")
    var estado: Boolean? = null,
)
