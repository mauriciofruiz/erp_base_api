package erp.base.api.models.rrh;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("RRH_TIPOLICENCIACONDUCIR")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RrhTipoLicenciaConducir {
    @Id
    @Column("ID_TIPOLICENCIACONDUCIR")
    @JsonProperty("drivingLicenseTypeId")
    private Integer idTipoLicenciaConducir;

    @Column("NOMBRE_TIPOLICENCIACONDUCIR")
    @JsonProperty("drivingLicenseTypeName")
    private String nombreTipoLicenciaConducir;

    @Column("DESCRIPCION_TIPOLICENCIACONDUCIR")
    @JsonProperty("drivingLicenseTypeDescription")
    private String descripcionTipoLicenciaConducir;
}
