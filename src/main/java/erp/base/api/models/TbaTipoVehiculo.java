package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TIPOVEHICULO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class TbaTipoVehiculo extends BaseEntity {
    @Id
    @Column("ID_TIPOVEHICULO")
    @JsonProperty("vehicleTypeId")
    private Integer idTipoVehiculo;

    @Column("NOMBRE_TIPOVEHICULO")
    @JsonProperty("vehicleTypeName")
    private String nombreTipoVehiculo;
}
