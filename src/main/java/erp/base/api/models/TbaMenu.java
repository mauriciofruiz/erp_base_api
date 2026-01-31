package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("MENU")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TbaMenu {

    @Id
    @Column("ID_MENU")
    @JsonProperty("menuId")
    private Integer idMenu;

    @Column("ICON_MENU")
    @JsonProperty("menuIcon")
    private String iconMenu;

    @Column("NIVEL_MENU")
    @JsonProperty("menuLevel")
    private Integer nivelMenu;

    @Column("NOMBRE_MENU")
    @JsonProperty("menuName")
    private String nombreMenu;

    @Column("URL_MENU")
    @JsonProperty("menuUrl")
    private String urlMenu;

    @Column("PADRE_MENU")
    @JsonProperty("menuFatherId")
    private Integer padreMenu;

    @Column("COMPONENT_MENU")
    @JsonProperty("menuComponent")
    private String componenteMenu;

    @Column("ORDEN_MENU")
    @JsonProperty("menuOrder")
    private Integer ordenMenu;

    @Column("ESTADO_MENU")
    @JsonProperty("menuStatus")
    private Boolean estadoMenu;
}
