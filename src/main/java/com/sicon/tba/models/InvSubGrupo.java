package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("INV_SUBGRUPO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InvSubGrupo {
    @Id
    @Column("ID_SUBGRUPO")
    @JsonProperty("subGroupId")
    private Integer idSubGrupo;

    @Column("ID_GRUPO")
    @JsonProperty("groupId")
    private Integer idGrupo;

    @Column("NOMBRE_SUBGRUPO")
    @JsonProperty("subGroupName")
    private String nombreSubGrupo;

    @Column("NEMONICO_SUBGRUPO")
    @JsonProperty("subGroupMnemonic")
    private String nemonicoSubGrupo;
}
