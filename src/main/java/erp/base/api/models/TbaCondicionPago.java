package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_CONDICIONPAGO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaCondicionPago {
    @Id
    @Column("ID_CONDICIONPAGO")
    @JsonProperty("paymentConditionId")
    private Integer idCondicionPago;

    @Column("NOMBRE_CONDICIONPAGO")
    @JsonProperty("paymentConditionName")
    private String nombreCondicionPago;
}
