package erp.base.api.models.rrh;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table("CONTRATOLABORAL")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RrhContratoLaboral {
    @Id
    @Column("ID_CONTRATO")
    @JsonProperty("contractId")
    private Integer idContrato;

    @Column("ID_TIPOCONTRATO")
    @JsonProperty("contractTypeId")
    private Integer idTipoContrato;

    @Column("ID_MODALIDADCONTRATO")
    @JsonProperty("contractModeId")
    private Integer idModalidadContrato;

    @Column("ID_EMPLEADO")
    @JsonProperty("employeeId")
    private Integer idEmpleado;

    @Column("FECHAINICIO_CONTRATO")
    @JsonProperty("contractStartDate")
    private LocalDate fechaInicioContrato;

    @Column("FINPRUEBA_CONTRATO")
    @JsonProperty("contractProbationEndDate")
    private LocalDate finPruebaContrato;

    @Column("FECHAFIN_CONTRATO")
    @JsonProperty("contractEndDate")
    private LocalDate fechaFinContrato;

    @Column("ARCHIVO_CONTRATO")
    @JsonProperty("contractFile")
    private byte[] archivoContrato;

    @Column("ESTADO_CONTRATO")
    @JsonProperty("contractStatus")
    private Boolean estadoContrato;

    @Column("NROHORAS_CONTRATO")
    @JsonProperty("contractHours")
    private BigDecimal numeroHorasContrato;
}
