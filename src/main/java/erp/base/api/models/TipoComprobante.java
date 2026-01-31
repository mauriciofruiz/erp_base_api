package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TIPOCOMPROBANTE")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TipoComprobante {
    @Id
    @Column("ID_TIPOCOMPROBANTE")
    @JsonProperty("voucherTypeId")
    private Integer idTipoComprobante;

    @Column("NOMBRE_TIPOCOMPROBANTE")
    @JsonProperty("voucherTypeName")
    private String nombreTipoComprobante;

    @Column("COD_TIPOCOMPROBANTE")
    @JsonProperty("voucherTypeCode")
    private String codigoTipoComprobante;
}
