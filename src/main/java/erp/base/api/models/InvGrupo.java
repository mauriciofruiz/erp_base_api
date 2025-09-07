package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("INV_GRUPO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InvGrupo {
    @Id
    @Column("ID_GRUPO")
    @JsonProperty("groupId")
    private Integer idGrupo;

    @Column("NOMBRE_GRUPO")
    @JsonProperty("groupName")
    private String nombreGrupo;

    @Column("NEMONICO_GRUPO")
    @JsonProperty("groupNemónico")
    private String nemonicoGrupo;

    @Column("CTAING_GRUPO")
    @JsonProperty("groupCtaIng")
    private Integer ctaIngGrupo;

    @Column("CTAINV_GRUPO")
    @JsonProperty("groupCtaInv")
    private Integer ctaInvGrupo;

    @Column("CTACOS_GRUPO")
    @JsonProperty("groupCtaCos")
    private Integer ctaCosGrupo;
}
