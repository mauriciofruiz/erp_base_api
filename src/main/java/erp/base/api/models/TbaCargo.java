package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("CARGO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaCargo {
    @Id
    @Column("ID_CARGO")
    @JsonProperty("positionId")
    private Integer idCargo;

    @Column("ID_DEPARTAMENTO")
    @JsonProperty("departmentId")
    private Integer idDepartamento;

    @Column("NOMBRE_CARGO")
    @JsonProperty("positionName")
    private String nombreCargo;

    @Column("ESTADO_CARGO")
    @JsonProperty("positionState")
    private Boolean estadoCargo;
}
