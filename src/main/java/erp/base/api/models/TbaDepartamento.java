package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("DEPARTAMENTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaDepartamento {
    @Id
    @Column("ID_DEPARTAMENTO")
    @JsonProperty("departmentId")
    private Integer idDepartamento;

    @Column("NOMBRE_DEPARTAMENTO")
    @JsonProperty("departmentName")
    private String nombreDepartamento;

    @Column("ESTADO_DEPARTAMENTO")
    @JsonProperty("departmentStatus")
    private Boolean estadoDepartamento;

    @Column("ID_AREAGRUPO")
    @JsonProperty("areaGroupId")
    private Integer idAreaGrupo;
}
