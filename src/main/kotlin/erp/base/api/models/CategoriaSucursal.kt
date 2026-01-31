package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("CATEGORIASUCURSAL")
data class CategoriaSucursal(
    @Id
    @Column("ID_CATEGORIASUCURSAL")
    @JsonProperty("branchCategoryId")
    val idCategoriaSucursal: String? = null,

    @Column("NOMBRE_CATEGORIASUCURSAL")
    @JsonProperty("branchCategoryName")
    val nombreCategoriaSucursal: String? = null,

    @Column("DESCRIPCION_CATEGORIASUCURSAL")
    @JsonProperty("branchCategoryDescription")
    val descripcionCategoriaSucursal: String? = null,
)
