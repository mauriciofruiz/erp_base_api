package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbaCargoDto {

    @NotNull(message = "El Id del cargo no puede ser nulo")
    @JsonProperty("departmentId")
    private Integer idDepartamento;

    @JsonProperty("positionName")
    private String nombreCargo;

    @JsonProperty("positionState")
    private Boolean estadoCargo;
}
