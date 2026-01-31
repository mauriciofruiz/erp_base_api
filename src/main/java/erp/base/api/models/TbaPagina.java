package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("PAGINA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaPagina {
    @Id
    @Column("ID_PAGINA")
    @JsonProperty("pageId")
    private Integer idPagina;

    @Column("URL_PAGINA")
    @JsonProperty("pageUrl")
    private String urlPagina;

    @Column("ALIAS_PAGINA")
    @JsonProperty("pageAlias")
    private String aliasPagina;

    @Column("SECCION_PAGINA")
    @JsonProperty("pageSection")
    private String seccionPagina;
}
