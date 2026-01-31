package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal

@Table("MODIFICACIONCREDITOCLIENTE")
data class ModificacionCreditoCliente(
    @Id
    @Column("ID_MODIFICACIONCREDITOCLIENTE")
    @JsonProperty("modificationId")
    val idModificacionCreditoCliente: Int,
    @Column("ID_CREDITOCLIENTE")
    @JsonProperty("creditClientId")
    val idCreditoCliente: Int? = null,
    @Column("ID_USR")
    @JsonProperty("userId")
    val idUsuario: Int? = null,
    @Column("CAMPOMODIFICADO")
    @JsonProperty("modifiedField")
    val campoModificado: String? = null,
    @Column("VALORANTERIOR_MODIFICACIONCREDCLIENTE")
    @JsonProperty("previousValue")
    val valorAnteriorModificacionCreditoCliente: BigDecimal? = null,
    @Column("NUEVOVALOR_MODIFICACIONCREDITOCLIENTE")
    @JsonProperty("newValue")
    val nuevoValorModificacionCreditoCliente: BigDecimal? = null,
)
