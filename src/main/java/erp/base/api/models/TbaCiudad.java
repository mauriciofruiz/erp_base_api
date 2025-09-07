package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_CIUDAD")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaCiudad {
    @Id
    @Column("ID_CIUDAD")
    @JsonProperty("cityId")
    private Integer idCiudad;

    @Column("ID_PROVINCIA")
    @JsonProperty("provinceId")
    private String idProvincia;

    @Column("NOMBRE_CIUDAD")
    @JsonProperty("cityName")
    private String nombreCiudad;

    @Column("CODIGO_CIUDAD")
    @JsonProperty("cityCode")
    private String codigoCiudad;
}
