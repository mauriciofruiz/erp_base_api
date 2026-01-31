package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("PARROQUIA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaParroquia {
    @Id
    @Column("ID_PARROQUIA")
    @JsonProperty("parishId")
    private Integer idParroquia;

    @Column("ID_CIUDAD")
    @JsonProperty("cityId")
    private Integer idCiudad;

    @Column("NOMBRE_PARROQUIA")
    @JsonProperty("parishName")
    private String nombreParroquia;

    @Column("COD_PARROQUIA")
    @JsonProperty("parishCode")
    private String codParroquia;
}
