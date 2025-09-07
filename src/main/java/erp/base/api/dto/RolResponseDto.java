package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolResponseDto {

    @JsonProperty("roleId")
    private Integer idRol;

    @JsonProperty("roleName")
    private String nombreRol;

    @JsonProperty("roleDescription")
    private String descripcionRol;

}
