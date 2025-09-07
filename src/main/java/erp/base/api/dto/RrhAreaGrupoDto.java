package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RrhAreaGrupoDto {

    @NotNull(message = "El nombre no puede estar vacío")
    @JsonProperty("areaGroupName")
    private String nombreAreaGrupo;
}
