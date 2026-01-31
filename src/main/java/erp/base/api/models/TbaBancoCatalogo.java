package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Table("BANCOCATALOGO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaBancoCatalogo extends BaseEntity {
    @Id
    @Column("ID_BANCOCATALOGO")
    @JsonProperty("bankCatalogId")
    private Integer idBancoCatalogo;

    @Column("NOMBRE_BANCOCATALOGO")
    @JsonProperty("bankCatalogName")
    private String nombreBancoCatalogo;

    @Column("CODIGO_BANCOCATALOGO")
    @JsonProperty("bankCatalogCode")
    private String codigoBancoCatalogo;

    @Column("estado_banco")
    @JsonProperty("bankStatus")
    private Boolean estadoBanco;
}
