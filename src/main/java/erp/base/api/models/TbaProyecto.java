package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("TBA_PROYECTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaProyecto {
    @Id
    @Column("ID_PROYECTO")
    @JsonProperty("projectId")
    private Integer idProyecto;

    @Column("NOMBRE_PROYECTO")
    @JsonProperty("projectName")
    private String nombreProyecto;

    @Column("DESCRIPCION_PROYECTO")
    @JsonProperty("projectDescription")
    private String descripcionProyecto;

    @Column("FECHAINICIO_PROYECTO")
    @JsonProperty("projectStartDate")
    private LocalDate fechaInicioProyecto;

    @Column("FECHAFIN_PROYECTO")
    @JsonProperty("projectEndDate")
    private LocalDate fechaFinProyecto;

    @Column("ESTADO_PROYECTO")
    @JsonProperty("projectStatus")
    private Boolean estadoProyecto;
}
