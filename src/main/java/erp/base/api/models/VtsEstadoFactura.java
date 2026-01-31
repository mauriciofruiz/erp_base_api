package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("ESTADOFACTURA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VtsEstadoFactura {
    @Id
    @Column("ID_ESTADOFACTURA")
    @JsonProperty("invoiceStatusId")
    private Integer idEstadoFactura;

    @Column("NOMBRE_ESTADOFACTURA")
    @JsonProperty("invoiceStatusName")
    private String nombreEstadoFactura;

    @Column("COD_ESTADOFACTURA")
    @JsonProperty("invoiceStatusCode")
    private String codEstadoFactura;
}
