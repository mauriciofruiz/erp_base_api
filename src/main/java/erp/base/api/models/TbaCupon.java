package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_CUPON")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaCupon {
    @Id
    @Column("ID_CUPON")
    @JsonProperty("couponId")
    private Integer idCupon;

    @Column("ANULADO_CUPON")
    @JsonProperty("isCancelled")
    private Boolean anulado;

    @Column("ENVIADOCORREO_CUPON")
    @JsonProperty("isEmailSent")
    private Boolean enviadoCorreo;

    @Column("NUM_CUPON")
    @JsonProperty("couponNumber")
    private Integer numCupon;

    @Column("ID_FACTURA_CUPON")
    @JsonProperty("invoiceId")
    private Integer idFacturaCupon;

    @Column("SORTEOACTIVO_CUPON")
    @JsonProperty("isLotteryActive")
    private Boolean sorteoActivo;
}
