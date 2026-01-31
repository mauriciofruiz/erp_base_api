package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("SUCURSAL")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Sucursal {
    @Id
    @Column("ID_SUCURSAL")
    @JsonProperty("branchId")
    private Integer idSucursal;

    @Column("ID_CIUDAD")
    @JsonProperty("cityId")
    private Integer idCiudad;

    @Column("ID_CATEGORIASUCURSAL")
    @JsonProperty("branchCategoryId")
    private String idCategoriaSucursal;

    @Column("ID_REGION")
    @JsonProperty("regionId")
    private Integer idRegion;

    @Column("NOMBRE_SUCURSAL")
    @JsonProperty("name")
    private String nombreSucursal;

    @Column("NEMONICO_SUCURSAL")
    @JsonProperty("mnemonic")
    private String nemonicoSucursal;

    @Column("DIRECCION_SUCURSAL")
    @JsonProperty("address")
    private String direccionSucursal;

    @Column("TELEFONO_SUCURSAL")
    @JsonProperty("phone")
    private String telefonoSucursal;

    @Column("TELEFONO2_SUCURSAL")
    @JsonProperty("phone2")
    private String telefono2Sucursal;

    @Column("CELULAR_SUCURSAL")
    @JsonProperty("cellphone")
    private String celularSucursal;

    @Column("CORREO_SUCURSAL")
    @JsonProperty("email")
    private String correoSucursal;

    @Column("CORREO2_SUCURSAL")
    @JsonProperty("email2")
    private String correo2Sucursal;

    @Column("RESPONSABLE_SUCURSAL")
    @JsonProperty("responsible")
    private String responsableSucursal;

    @Column("ESOUTLET_SUCURSAL")
    @JsonProperty("isOutlet")
    private Boolean esOutletSucursal;

    @Column("ESTADO_SUCURSAL")
    @JsonProperty("state")
    private Boolean estadoSucursal;

    @Column("IMPRESIONTERMICA_SUCURSAL")
    @JsonProperty("thermalPrinting")
    private Boolean impresionTermicaSucursal;

    @Column("codigo_sucursal")
    @JsonProperty("branchCode")
    private String codigoSucursal;
}
