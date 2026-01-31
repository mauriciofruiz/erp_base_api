package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("ESTADO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaEstado {
    @Id
    @Column("ID_ESTADO")
    @JsonProperty("stateId")
    private Integer idEstado;

    @Column("NOMBRE_ESTADO")
    @JsonProperty("stateName")
    private String nombreEstado;
}

