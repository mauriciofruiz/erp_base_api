package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_PARAMETRO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaParametro {
    @Id
    @Column("ID_PARAMETRO")
    @JsonProperty("parameterId")
    private Integer idParametro;

    @Column("NOMBRE_PARAMETRO")
    @JsonProperty("parameterName")
    private String nombreParametro;

    @Column("VALOR_PARAMETRO")
    @JsonProperty("parameterValue")
    private String valorParametro;
}
