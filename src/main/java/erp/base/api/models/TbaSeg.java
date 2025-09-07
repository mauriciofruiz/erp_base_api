package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_SEG")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaSeg {
    @Id
    @Column("ID_SEG")
    @JsonProperty("segId")
    private Integer idSeg;

    @Column("TIEMPOSESION_SEG")
    @JsonProperty("sessionTime")
    private String tiempoSesionSeg;

    @Column("HORADESDE_SEG")
    @JsonProperty("startTime")
    private String horaDesdeSeg;

    @Column("HORAHASTA_SEG")
    @JsonProperty("endTime")
    private String horaHastaSeg;
}
