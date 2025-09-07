package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_CATEGORIASUCURSAL")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaCategoriaSucursal {
    @Id
    @Column("ID_CATEGORIASUCURSAL")
    @JsonProperty("branchCategoryId")
    private String idCategoriaSucursal;

    @Column("NOMBRE_CATEGORIASUCURSAL")
    @JsonProperty("branchCategoryName")
    private String nombreCategoriaSucursal;

    @Column("DESCRIPCION_CATEGORIASUCURSAL")
    @JsonProperty("branchCategoryDescription")
    private String descripcionCategoriaSucursal;
}
