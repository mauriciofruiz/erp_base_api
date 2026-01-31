package erp.base.api.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import erp.base.api.dto.BaseDto
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransporteResponse(
    @JsonProperty("creationUserId")
    override val idUsuarioCreacion: Int? = null,
    @JsonProperty("modificationUserId")
    override val idUsuarioModificacion: Int? = null,
    @JsonProperty("registrationDate")
    override val fechaCreacion: LocalDateTime? = null,
    @JsonProperty("modificationDate")
    override val fechaModificacion: LocalDateTime? = null,
    @JsonProperty("transportId")
    var idTransporte: Int? = null,
    @JsonProperty("vehicleTypeId")
    var idTipoVehiculo: Int? = null,
    @JsonProperty("vehicleTypeName")
    var nombreTipoVehiculo: String? = null,
    @JsonProperty("contactName")
    var nombreContacto: String? = null,
    @JsonProperty("contactId")
    var identificacionContacto: String? = null,
    @JsonProperty("contactNumber")
    var numeroContacto: String? = null,
    @JsonProperty("licensePlate")
    var placaTransporte: String? = null,
    @JsonProperty("sector")
    var sectorTransporte: String? = null,
    @JsonProperty("province")
    var provinciaTransporte: String? = null,
    @JsonProperty("details")
    var detalleTransporte: String? = null,
    @JsonProperty("minWeight")
    var pesoMinimoTransporte: BigDecimal? = null,
    @JsonProperty("maxWeight")
    var pesoMaximoTransporte: BigDecimal? = null,
    @JsonProperty("registration")
    var matriculaTransporte: String? = null,
    @JsonProperty("internal")
    var internoTransporte: Boolean? = null,
    @JsonProperty("inactive")
    var inactivoTransporte: Boolean? = null,
) : BaseDto(idUsuarioCreacion, idUsuarioModificacion, fechaCreacion, fechaModificacion)
