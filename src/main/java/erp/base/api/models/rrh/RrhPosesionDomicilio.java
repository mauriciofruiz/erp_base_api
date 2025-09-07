package erp.base.api.models.rrh;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("RRH_POSESIONDOMICILIO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RrhPosesionDomicilio {
    @Id
    @Column("ID_POSESIONDOMICILIO")
    @JsonProperty("homePossessionId")
    private Integer idPosesionDomicilio;

    @Column("NOMBRE_POSESIONDOMICILIO")
    @JsonProperty("homePossessionName")
    private String nombrePosesionDomicilio;
}
