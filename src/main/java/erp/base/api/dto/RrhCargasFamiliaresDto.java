package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RrhCargasFamiliaresDto {

    @JsonProperty("familyBurdenId")
    private Integer idCargafml;

    @JsonProperty("idKind")
    private Integer idParentesco;

    @JsonProperty("relativeName")
    private String nombreParentesco;

    @JsonProperty("identification")
    private String cedulaCargafml;

    @JsonProperty("names")
    private String nombreCargafml;

    @JsonProperty("personBirthDate")
    private LocalDate fechanacimientoCargafml ;

    @JsonProperty("especialCapacity")
    private Boolean capacidadespecialCargafml;

    @JsonProperty("enableBurdenFml")
    private Boolean activaCargafml;

    @JsonProperty("age")
    private Integer edad;

}
