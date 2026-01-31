package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("TIPOPROVEEDOR")
data class TipoProveedor(
    @Id
    @Column("ID_TIPOPROV")
    @JsonProperty("vendorTypeId")
    val idTipoProveedor: Int? = null,

    @Column("NOMBRE_TIPOPROV")
    @JsonProperty("vendorTypeName")
    val nombreTipoProveedor: String? = null,
)
