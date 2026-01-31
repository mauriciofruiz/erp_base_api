package erp.base.api.models.rrh;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.r2dbc.spi.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("CARGAFAMILIAR")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RrhCargaFamiliar {

    @Id
    @Column("ID_CARGAFML")
    @JsonProperty("familyBurdenId")
    private Integer idCargaFml;

    @Column("ID_PARENTESCO")
    @JsonProperty("kinId")
    private Integer idParentesco;

    @Column("ID_EMPLEADO")
    @JsonProperty("employeeId")
    private Integer idEmpleado;

    @Column("NOMBRE_CARGAFML")
    @JsonProperty("familyBurdenName")
    private String nombreCargaFml;

    @Column("CEDULA_CARGAFML")
    @JsonProperty("identificationFML")
    private String cedulaCargaFml;

    @Column("FECHANACIMIENTO_CARGAFML")
    @JsonProperty("birthdateBurdenFml")
    private LocalDate fechaNacimientoCargaFml;


    @Column("CAPACIDADESPECIAL_CARGAFML")
    @JsonProperty("capacityEspecialBurdenFml")
    private Boolean capacidadEspecialCargaFml;

    @Column("ARCHIVO_CARGAFML")
    @JsonProperty("fileBurdenFml")
    private String archivoCargaFml;

    @Column("ACTIVA_CARGAFML")
    @JsonProperty("enableBurdenFml")
    private Boolean activaCargaFml;


}
