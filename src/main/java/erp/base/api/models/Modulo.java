package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Table("tba_modulo")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Modulo extends BaseEntity {

    @Id
    @Column("id_modulo")
    @JsonProperty("moduleId")
    private Integer idModulo;

    @Column("nombre_modulo")
    @JsonProperty("moduleName")
    private String nombreModulo;

}
