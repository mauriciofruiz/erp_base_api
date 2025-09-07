package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TRB_TIPODOC")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TrbTipoDoc {
    @Id
    @Column("ID_TIPODOC")
    @JsonProperty("documentTypeId")
    private Integer idTipoDoc;

    @Column("COD_TIPODOC")
    @JsonProperty("documentTypeCode")
    private String codigoTipoDoc;

    @Column("NOMBRE_TIPODOC")
    @JsonProperty("documentTypeName")
    private String nombreTipoDoc;
}
