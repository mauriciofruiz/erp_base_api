package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_PAGOROL")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaPagoRol {
    @Id
    @Column("ID_PAGOROLES")
    @JsonProperty("paymentRoleId")
    private Integer idPagoRoles;

    @Column("ID_PAGO")
    @JsonProperty("paymentId")
    private Integer idPago;

    @Column("ID_ROLPAGO")
    @JsonProperty("paymentRoleId")
    private Integer idRolPago;
}
