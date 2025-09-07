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
public class TbaMenuUsuarioResponseDto {

    @JsonProperty("menuId")
    private Integer idMenu;

    @JsonProperty("menuName")
    private String nombreMenu;

    @JsonProperty("menuFatherId")
    private Integer padreMenu;

    @JsonProperty("menuLevel")
    private Integer nivelMenu;

    @JsonProperty("menuIcon")
    private String iconMenu;

    @JsonProperty("menuUrl")
    private String urlMenu;

    @JsonProperty("menuComponent")
    private String componentMenu;

    @JsonProperty("menuState")
    private Boolean estadoMenuUsuario;



}
