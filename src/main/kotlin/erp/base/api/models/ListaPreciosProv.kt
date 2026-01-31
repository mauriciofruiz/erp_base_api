package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("LISTAPRECIOSPROV")
data class ListaPreciosProv(
    @Id
    @Column("ID_LISTAPRECIOS")
    @JsonProperty("priceListId")
    val idListaPrecios: Int,
    @Column("ID_PROVEEDOR")
    @JsonProperty("providerId")
    val idProveedor: Int? = null,
    @Column("ID_PRODUCTO")
    @JsonProperty("productId")
    val idProducto: Int? = null,
    @Column("RANGO_INICIAL")
    @JsonProperty("initialRange")
    val rangoInicial: Double? = null,
    @Column("RANGO_FINAL")
    @JsonProperty("finalRange")
    val rangoFinal: Double? = null,
    @Column("ACTUALIZADO")
    @JsonProperty("updated")
    val actualizado: Boolean? = null,
)
