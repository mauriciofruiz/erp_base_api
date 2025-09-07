package erp.base.api.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import erp.base.api.models.Proveedor;
import erp.base.api.models.TbaPersona;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbaProveedorResponseDto {

    @JsonProperty("provider")
    private Proveedor proveedor;

    @JsonProperty("person")
    private TbaPersona persona;

    @JsonProperty("parishName")
    private String nombreParroquia;

    @JsonProperty("cityId")
    private Integer idCiudad;

    @JsonProperty("cityName")
    private String nombreCiudad;

    @JsonProperty("provinceId")
    private String idProvincia;

    @JsonProperty("provinceName")
    private String nombreProvincia;

    @JsonProperty("accountPayableStatus")
    private EstadoCuentoPagarDto estadoCuentoPagar;

    @JsonProperty("numberInvoices")
    private Integer numeroFacturas;
}
