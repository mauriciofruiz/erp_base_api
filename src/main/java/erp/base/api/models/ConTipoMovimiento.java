package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("CON_TIPOMOVIMIENTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ConTipoMovimiento {
    @Id
    @Column("ID_TIPOMOVIMIENTO")
    @JsonProperty("movementTypeId")
    private Integer idTipoMovimiento;

    @Column("NOMBRE_TIPOMOVIMIENTO")
    @JsonProperty("movementTypeName")
    private String nombreTipoMovimiento;
}
