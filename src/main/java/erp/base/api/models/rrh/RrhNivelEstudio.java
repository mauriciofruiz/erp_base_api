package erp.base.api.models.rrh;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("RRH_NIVELESTUDIO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RrhNivelEstudio {
    @Id
    @Column("ID_NIVELESTUDIO")
    @JsonProperty("educationLevelId")
    private Integer idNivelEstudio;

    @Column("NOMBRE_NIVELESTUDIO")
    @JsonProperty("educationLevelName")
    private String nombreNivelEstudio;
}
