package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("ROTACIONPRODUCTO")
data class RotacionProducto(
    @Id
    @Column("ID_ROTACIONPRODUCTO")
    @JsonProperty("rotationProductId")
    val idRotacionProducto: Int,
    @Column("NOMBRE_ROTACIONPRODUCTO")
    @JsonProperty("rotationProductName")
    val nombreRotacionProducto: String? = null,
)
