package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_ROLUSUARIO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class TbaRolUsuario extends BaseEntity {
    @Id
    @Column("ID_ROLUSUARIO")
    @JsonProperty("roleUserId")
    private Integer idRolUsuario;

    @Column("ID_USR")
    @JsonProperty("userId")
    private Integer idUsuario;

    @Column("ID_ROL")
    @JsonProperty("roleId")
    private Integer idRol;

    @Column("ESTADO_ROLUSUARIO")
    @JsonProperty("roleUserStatus")
    private Boolean estadoRolUsuario;
}
