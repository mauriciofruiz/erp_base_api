package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("GENERO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaGenero {
    @Id
    @Column("ID_GENERO")
    @JsonProperty("genderId")
    private Integer idGenero;

    @Column("NOMBRE_GENERO")
    @JsonProperty("genderName")
    private String nombreGenero;

    @Column("COD_GENERO")
    @JsonProperty("genderCode")
    private String codigoGenero;
}
