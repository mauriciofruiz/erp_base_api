package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TRB_TIPOCONTRIBUYENTE")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TrbTipoContribuyente {
    @Id
    @Column("ID_TIPOCONTRIBUYENTE")
    @JsonProperty("contributorTypeId")
    private String idTipoContribuyente;

    @Column("NOMBRE_TIPOCONTRIBUYENTE")
    @JsonProperty("contributorTypeName")
    private String nombreTipoContribuyente;

    @Column("COD_TIPOCONTRIBUYENTE")
    @JsonProperty("contributorTypeCode")
    private String codigoTipoContribuyente;
}
