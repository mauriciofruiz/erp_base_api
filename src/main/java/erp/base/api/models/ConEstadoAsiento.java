package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("ESTADOASIENTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ConEstadoAsiento {
    @Id
    @Column("ID_ESTADOASIENTO")
    @JsonProperty("accountingEntryStateId")
    private Integer idEstadoAsiento;

    @Column("NOMBRE_ESTADOASIENTO")
    @JsonProperty("accountingEntryStateName")
    private String nombreEstadoAsiento;
}

