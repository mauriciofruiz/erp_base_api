package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_DESBLOQUEO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaDesbloqueo {
    @Id
    @Column("ID_DESBLOQUEO")
    @JsonProperty("unlockId")
    private Integer idDesbloqueo;

    @Column("IP_DESBLOQUEO")
    @JsonProperty("unlockIp")
    private String ipDesbloqueo;

    @Column("DESCRIPCION_DESBLOQUEO")
    @JsonProperty("unlockDescription")
    private String descripcionDesbloqueo;

    @Column("ESTADO_DESBLOQUEO")
    @JsonProperty("unlockStatus")
    private Boolean estadoDesbloqueo;
}
