package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("SUCURSAL")
data class Sucursal(
    @Id
    @Column("ID_SUCURSAL")
    @JsonProperty("branchId")
    var idSucursal: Int? = null,
    @Column("ID_CIUDAD")
    @JsonProperty("cityId")
    var idCiudad: Int? = null,
    @Column("ID_CATEGORIASUCURSAL")
    @JsonProperty("branchCategoryId")
    var idCategoriaSucursal: String? = null,
    @Column("ID_REGION")
    @JsonProperty("regionId")
    var idRegion: Int? = null,
    @Column("NOMBRE_SUCURSAL")
    @JsonProperty("name")
    var nombreSucursal: String? = null,
    @Column("NEMONICO_SUCURSAL")
    @JsonProperty("mnemonic")
    var nemonicoSucursal: String? = null,
    @Column("DIRECCION_SUCURSAL")
    @JsonProperty("address")
    var direccionSucursal: String? = null,
    @Column("TELEFONO_SUCURSAL")
    @JsonProperty("phone")
    var telefonoSucursal: String? = null,
    @Column("TELEFONO2_SUCURSAL")
    @JsonProperty("phone2")
    var telefono2Sucursal: String? = null,
    @Column("CELULAR_SUCURSAL")
    @JsonProperty("cellphone")
    var celularSucursal: String? = null,
    @Column("CORREO_SUCURSAL")
    @JsonProperty("email")
    var correoSucursal: String? = null,
    @Column("CORREO2_SUCURSAL")
    @JsonProperty("email2")
    var correo2Sucursal: String? = null,
    @Column("RESPONSABLE_SUCURSAL")
    @JsonProperty("responsible")
    var responsableSucursal: String? = null,
    @Column("ESOUTLET_SUCURSAL")
    @JsonProperty("isOutlet")
    var esOutletSucursal: Boolean? = null,
    @Column("ESTADO_SUCURSAL")
    @JsonProperty("state")
    var estadoSucursal: Boolean? = null,
    @Column("IMPRESIONTERMICA_SUCURSAL")
    @JsonProperty("thermalPrinting")
    var impresionTermicaSucursal: Boolean? = null,
    @Column("codigo_sucursal")
    @JsonProperty("branchCode")
    var codigoSucursal: String? = null,
)
