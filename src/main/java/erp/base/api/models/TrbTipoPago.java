package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TRB_TIPOPAGO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TrbTipoPago {
    @Id
    @Column("ID_TIPOPAGO")
    @JsonProperty("paymentTypeId")
    private Integer idTipoPago;

    @Column("COD_TIPOPAGO")
    @JsonProperty("paymentTypeCode")
    private String codigoTipoPago;

    @Column("NOMBRE_TIPOPAGO")
    @JsonProperty("paymentTypeName")
    private String nombreTipoPago;
}
