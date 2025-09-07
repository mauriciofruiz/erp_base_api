package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TbaMenuUsuarioDto {

    @NotNull(message = "El estado del menu usuario no puede ser nulo")
    @JsonProperty("menuUserStatus")
    private Boolean estadoMenuUsuario;

    @NotNull(message = "El id del menu no puede ser nulo")
    @JsonProperty("menuId")
    private Integer idMenu;

    @NotNull(message = "El id del usuario no puede ser nulo")
    @JsonProperty("userId")
    private Integer idUsuario;

}
