package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbaPaisDto {

    @NotNull(message = "El código del país no puede estar vacío")
    @JsonProperty("countryCode")
    private String codigoPais;

    @NotNull(message = "El nombre del país no puede estar vacío")
    @JsonProperty("countryName")
    private String nombrePais;
}
