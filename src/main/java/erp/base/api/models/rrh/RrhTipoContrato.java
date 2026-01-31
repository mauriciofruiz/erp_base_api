package erp.base.api.models.rrh;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TIPOCONTRATO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RrhTipoContrato {
    @Id
    @Column("ID_TIPOCONTRATO")
    @JsonProperty("contractTypeId")
    private Integer idTipoContrato;

    @Column("NOMBRE_TIPOCONTRATO")
    @JsonProperty("contractTypeName")
    private String nombreTipoContrato;
}
