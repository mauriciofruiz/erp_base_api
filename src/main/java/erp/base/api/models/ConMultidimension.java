package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("MULTIDIMENSION")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ConMultidimension {
    @Id
    @Column("ID_MULTIDIMENSION")
    @JsonProperty("multidimensionId")
    private Integer idMultidimension;

    @Column("TIPO_MULTIDIMENSION")
    @JsonProperty("multidimensionType")
    private String tipoMultidimension;

    @Column("VALOR_MULTIDIMENSION")
    @JsonProperty("multidimensionValue")
    private Integer valorMultidimension;
}
