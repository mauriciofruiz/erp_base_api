package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Table("tba_rol")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Rol extends BaseEntity {
    @Id
    @Column("id_rol")
    @JsonProperty("roleId")
    private Integer idRol;

    @Column("nombre_rol")
    @JsonProperty("roleName")
    private String nombreRol;

    @Column("descripcion_rol")
    @JsonProperty("roleDescription")
    private String descripcionRol;

    @Column("horainicio_rol")
    @JsonProperty("roleStartTime")
    private String horaInicioRol;

    @Column("horafin_rol")
    @JsonProperty("roleEndTime")
    private String horaFinRol;

    @Column("tiempo_rol")
    @JsonProperty("roleTime")
    private Integer tiempoRol;

}
