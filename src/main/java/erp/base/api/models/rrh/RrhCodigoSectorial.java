package erp.base.api.models.rrh;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("RRH_CODIGOSECTORIAL")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RrhCodigoSectorial {
    @Id
    @Column("ID_CODIGOSECTORIAL")
    @JsonProperty("sectoralCodeId")
    private Integer idCodigoSectorial;

    @Column("NOMBRE_CODIGOSECTORIAL")
    @JsonProperty("sectoralCodeName")
    private String nombreCodigoSectorial;

    @Column("COD_CODIGOSECTORIAL")
    @JsonProperty("sectoralCodeCod")
    private String codigoCodigoSectorial;
}
