package erp.base.api.models.rrh;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("AREAGRUPO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RrhAreaGrupo {
    @Id
    @Column("ID_AREAGRUPO")
    @JsonProperty("areaGroupId")
    private Integer idAreaGrupo;

    @Column("NOMBRE_AREAGRUPO")
    @JsonProperty("areaGroupName")
    private String nombreAreaGrupo;
}
