package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal

@Table("CATEGORIACLIENTE")
data class CategoriaCliente(
    @Id
    @Column("ID_CATEGORIACLIENTE")
    @JsonProperty("clientCategoryId")
    var idCategoriaCliente: Int? = null,

    @Column("NOMBRE_CATEGORIACLIENTE")
    @JsonProperty("clientCategoryName")
    var nombreCategoriaCliente: String? = null,

    @Column("DESCUENTO_CATEGORIACLIENTE")
    @JsonProperty("clientCategoryDiscount")
    var descuentoCategoriaCliente: BigDecimal? = null,

    @Column("CATEGORIAESPECIAL_CATEGORIACLIENTE")
    @JsonProperty("specialCategory")
    var categoriaEspecialCategoriaCliente: Boolean? = null,

    @Column("ACTIVOCATEGORIA_CATEGORIACLIENTE")
    @JsonProperty("categoryActive")
    var activoCategoriaCliente: Boolean? = null,
)
