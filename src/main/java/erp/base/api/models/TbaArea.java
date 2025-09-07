package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_AREA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaArea {
    @Id
    @Column("ID_AREA")
    @JsonProperty("areaId")
    private Integer idArea;

    @Column("NOMBRE_AREA")
    @JsonProperty("areaName")
    private String nombreArea;

    @Column("DESCRIPCION_AREA")
    @JsonProperty("areaDescription")
    private String descripcionArea;
}
