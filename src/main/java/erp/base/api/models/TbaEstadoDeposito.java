package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("ESTADODEPOSITO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaEstadoDeposito {
    @Id
    @Column("ID_ESTADODEPOSITO")
    @JsonProperty("depositStateId")
    private Integer idEstadoDeposito;

    @Column("NOMBRE_ESTADODEPOSITO")
    @JsonProperty("depositStateName")
    private String nombreEstadoDeposito;
}
