package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_PAIS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaPais {
    @Id
    @Column("ID_PAIS")
    @JsonProperty("countryId")
    private Integer idPais;

    @Column("CODIGOPAIS")
    @JsonProperty("countryCode")
    private String codigoPais;

    @Column("NOMBRE_PAIS")
    @JsonProperty("countryName")
    private String nombrePais;
}
