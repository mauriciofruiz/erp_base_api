package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("FORMAPAGOCOBRO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaFormaPagoCobro {
    @Id
    @Column("ID_FORMAPAGOCOBRO")
    @JsonProperty("paymentCollectionMethodId")
    private String idFormaPagoCobro;

    @Column("NOMBRE_FORMAPAGOCOBRO")
    @JsonProperty("paymentCollectionMethodName")
    private String nombreFormaPagoCobro;

    @Column("ALIAS_FORMAPAGOCOBRO")
    @JsonProperty("paymentCollectionMethodAlias")
    private String aliasFormaPagoCobro;

    @Column("DESCUENTO_FORMAPAGOCOBRO")
    @JsonProperty("paymentCollectionMethodDiscount")
    private BigDecimal descuentoFormaPagoCobro;

    @Column("COD_FORMAPAGOCOBRO")
    @JsonProperty("paymentCollectionMethodCode")
    private String codigoFormaPagoCobro;

    @Column("ESTADO_FORMAPAGOCOBRO")
    @JsonProperty("paymentCollectionMethodStatus")
    private Boolean estadoFormaPagoCobro;
}
