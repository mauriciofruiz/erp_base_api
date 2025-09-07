package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("CON_TIPOCUENTA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ConTipoCuenta {
    @Id
    @Column("ID_TIPOCUENTA")
    @JsonProperty("accountTypeId")
    private Integer idTipoCuenta;

    @Column("NOMBRE_TIPOCUENTA")
    @JsonProperty("accountTypeName")
    private String nombreTipoCuenta;

    @Column("DESCRIPCION_TIPOCUENTA")
    @JsonProperty("accountTypeDescription")
    private String descripcionTipoCuenta;
}
