package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("CON_ESTADOPAGO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ConEstadoPago {
    @Id
    @Column("ID_ESTADOPAGO")
    @JsonProperty("paymentStateId")
    private Integer idEstadoPago;

    @Column("NOMBRE_ESTADOPAGO")
    @JsonProperty("paymentStateName")
    private String nombreEstadoPago;
}
