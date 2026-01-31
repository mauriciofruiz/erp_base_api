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
@Table("rolusuario")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class RolUsuario extends BaseEntity {
    @Id
    @Column("id_rolusuario")
    @JsonProperty("roleUserId")
    private Integer idRolUsuario;

    @Column("id_usr")
    @JsonProperty("userId")
    private Integer idUsuario;

    @Column("id_rol")
    @JsonProperty("roleId")
    private Integer idRol;

    @Column("estado_rolusuario")
    @JsonProperty("roleUserState")
    private Boolean estadoRolUsuario;
}
