package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("MATRICULA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaMatricula {
    @Id
    @Column("ID_MATRICULA")
    @JsonProperty("registrationId")
    private Integer idMatricula;

    @Column("ID_VEHICULO")
    @JsonProperty("vehicleId")
    private Integer idVehiculo;

    @Column("FECHAEMISION_MATRICULA")
    @JsonProperty("registrationIssueDate")
    private LocalDate fechaEmisionMatricula;

    @Column("FECHACADUCA_MATRICULA")
    @JsonProperty("registrationExpirationDate")
    private LocalDate fechaCaducaMatricula;
}
