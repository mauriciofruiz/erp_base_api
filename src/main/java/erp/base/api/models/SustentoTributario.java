package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TRB_SUSTENTOTRIBUTARIO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SustentoTributario {
    @Id
    @Column("ID_SUSTENTOTRIBUTARIO")
    @JsonProperty("taxSupportId")
    private String idSustentoTributario;

    @Column("NOMBRE_SUSTENTOTRIBUTARIO")
    @JsonProperty("taxSupportName")
    private String nombreSustentoTributario;
}
