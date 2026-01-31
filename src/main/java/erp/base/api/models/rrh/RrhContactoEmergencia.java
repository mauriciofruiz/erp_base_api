package erp.base.api.models.rrh;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("CONTACTOEMERGENCIA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RrhContactoEmergencia {

    @Id
    @Column("ID_CONTACTOEMERGENCIA")
    @JsonProperty("contactEmergencyId")
    private Integer idContactoEmergencia;


    @Column("ID_EMPLEADO")
    @JsonProperty("employeeId")
    private Integer idEmpleado;

    @Column("ID_PARENTESCO")
    @JsonProperty("kinId")
    private Integer idParentesco;

    @Column("NOMBRE_CONTACTOEMERGENCIA")
    @JsonProperty("nameContactEmergency")
    private String nombreContactoEmergencia;

    @Column("CELULAR_CONTACTOEMERGENCIA")
    @JsonProperty("cellphoneContactEmergency")
    private String celularContactoEmergencia;


    @Column("TELEFONO_CONTACTOEMERGENCIA")
    @JsonProperty("phoneContactEmergency")
    private String telefonoContactoEmergencia;


}
