package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TbaUsuarioDto {

    @JsonProperty("branchId")
    private Integer idSucursal;

    @JsonProperty("personId")
    private Integer idPersona;

    @JsonProperty("nick")
    private String nickUsr;
}
