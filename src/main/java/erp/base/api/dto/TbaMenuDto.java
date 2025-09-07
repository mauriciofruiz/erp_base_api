package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbaMenuDto {

    @JsonProperty("menuId")
    private Integer idMenu;

    @JsonProperty("menuIcon")
    private String iconMenu;

    @JsonProperty("menuLevel")
    private Integer nivelMenu;

    @NotNull(message = "El nombre del menu no puede ser nulo")
    @JsonProperty("menuName")
    private String nombreMenu;

    @NotNull(message = "La url del menu no puede ser nula")
    @JsonProperty("menuUrl")
    private String urlMenu;

    @JsonProperty("menuFatherId")
    private Integer padreMenu;

    @JsonProperty("menuComponente")
    private String menuComponent;
}
