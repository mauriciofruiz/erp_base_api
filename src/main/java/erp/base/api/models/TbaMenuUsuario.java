package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("MENU_USUARIO")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TbaMenuUsuario {

    @Id
    @Column("ID_MENU_USUARIO")
    @JsonProperty("menuUserId")
    private Integer idMenuUsuario;

    @Column("ESTADO_MENU_USUARIO")
    @JsonProperty("menuUserStatus")
    private Boolean estadoMenuUsuario;

    @Column("ID_MENU")
    @JsonProperty("menuId")
    private Integer idMenu;

    @Column("ID_USUARIO")
    @JsonProperty("userId")
    private Integer idUsuario;
}
