package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("CASOSVERIFICACION")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaCasosVerificacion {
    @Id
    @Column("ID_CASOSVERIFICACION")
    @JsonProperty("verificationCaseId")
    private Integer idCasosVerificacion;

    @Column("NOMBRE_CASOSVERIFICACION")
    @JsonProperty("verificationCaseName")
    private String nombreCasosVerificacion;
}
