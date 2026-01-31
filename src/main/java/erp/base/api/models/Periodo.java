package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Table("PERIODO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Periodo extends BaseEntity {
    @Id
    @Column("ID_PERIODO")
    @JsonProperty("periodId")
    private Integer idPeriodo;

    @Column("FECHACIERRE_PERIODO")
    @JsonProperty("closingPeriodDate")
    private LocalDate fechaCierrePeriodo;

    @Column("FECHAANTERIOR_PERIODO")
    @JsonProperty("previousPeriodDate")
    private LocalDate fechaAnteriorPeriodo;

    @Column("id_modulo")
    @JsonProperty("moduleId")
    private Integer idModulo;
}
