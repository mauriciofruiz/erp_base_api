package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrbIdentificacionFiscalDto {

    @NotNull(message = "El código de identificación fiscal no puede estar vacío.")
    @JsonProperty("taxIdentificationCode")
    private String codIdentificacionFiscal;

    @NotNull(message = "El nombre de identificación fiscal no puede estar vacío.")
    @JsonProperty("taxIdentificationName")
    private String nombreIdentificacionFiscal;

    @NotNull(message = "El código de identificación fiscal de ATS no puede estar vacío.")
    @JsonProperty("atsIdentificationCode")
    private String codAtsIdentificacionFiscal;
}
