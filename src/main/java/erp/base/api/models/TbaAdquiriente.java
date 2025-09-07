package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Table("TBA_ADQUIRIENTE")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaAdquiriente extends BaseEntity {
    @Id
    @Column("ID_ADQUIRIENTE")
    @JsonProperty("acquirerId")
    private Integer idAdquiriente;

    @Column("NOMBRE_ADQUIRIENTE")
    @JsonProperty("acquirerName")
    private String nombreAdquiriente;

    @Column("estado_adquiriente")
    @JsonProperty("acquirerStatus")
    private Boolean estadoAdquiriente;
}
