package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("PAGINAROL")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaPaginaRol {
    @Id
    @Column("ID_PAGINAROL")
    @JsonProperty("pageRoleId")
    private Integer idPaginaRol;

    @Column("ID_ROL")
    @JsonProperty("roleId")
    private Integer idRol;

    @Column("ID_PAGINA")
    @JsonProperty("pageId")
    private Integer idPagina;

    @Column("ESTADO_PAGINAROL")
    @JsonProperty("pageRoleState")
    private Boolean estadoPaginaRol;
}
