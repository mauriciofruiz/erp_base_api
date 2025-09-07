package erp.base.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import erp.base.api.dto.BaseDto;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;



@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class TbaTransporteResponse extends BaseDto {

    @JsonProperty("transportId")
    private Integer idTransporte;

    @JsonProperty("vehicleTypeId")
    private Integer idTipoVehiculo;

    @JsonProperty("vehicleTypeName")
    private String nombreTipoVehiculo;

    @JsonProperty("contactName")
    private String nombreContacto;

    @JsonProperty("contactId")
    private String identificacionContacto;

    @JsonProperty("contactNumber")
    private String numeroContacto;

    @JsonProperty("licensePlate")
    private String placaTransporte;

    @JsonProperty("sector")
    private String sectorTransporte;

    @JsonProperty("province")
    private String provinciaTransporte;

    @JsonProperty("details")
    private String detalleTransporte;

    @JsonProperty("minWeight")
    private BigDecimal pesoMinimoTransporte;

    @JsonProperty("maxWeight")
    private BigDecimal pesoMaximoTransporte;

    @JsonProperty("registration")
    private String matriculaTransporte;

    @JsonProperty("internal")
    private Boolean internoTransporte;

    @JsonProperty("inactive")
    private Boolean inactivoTransporte;

}

