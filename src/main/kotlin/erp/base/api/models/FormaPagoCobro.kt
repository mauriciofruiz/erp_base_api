package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal

@Table("FORMAPAGOCOBRO")
data class FormaPagoCobro(
    @Id
    @Column("ID_FORMAPAGOCOBRO")
    @JsonProperty("paymentCollectionMethodId")
    var idFormaPagoCobro: String? = null,

    @Column("NOMBRE_FORMAPAGOCOBRO")
    @JsonProperty("paymentCollectionMethodName")
    var nombreFormaPagoCobro: String? = null,

    @Column("ALIAS_FORMAPAGOCOBRO")
    @JsonProperty("paymentCollectionMethodAlias")
    var aliasFormaPagoCobro: String? = null,

    @Column("DESCUENTO_FORMAPAGOCOBRO")
    @JsonProperty("paymentCollectionMethodDiscount")
    var descuentoFormaPagoCobro: BigDecimal? = null,

    @Column("COD_FORMAPAGOCOBRO")
    @JsonProperty("paymentCollectionMethodCode")
    var codigoFormaPagoCobro: String? = null,

    @Column("ESTADO_FORMAPAGOCOBRO")
    @JsonProperty("paymentCollectionMethodStatus")
    var estadoFormaPagoCobro: Boolean? = null,
)
