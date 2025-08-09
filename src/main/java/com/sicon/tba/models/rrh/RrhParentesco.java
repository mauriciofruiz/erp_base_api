package com.sicon.tba.models.rrh;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("RRH_PARENTESCO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RrhParentesco {

    @Id
    @Column("ID_PARENTESCO")
    @JsonProperty("kinId")
    private Integer idParentesco;

    @Column("NOMBRE_PARENTESCO")
    @JsonProperty("kinName")
    private String nombreParentesco;

    @Column("CARGAFAMILIAR_PARENTESCO")
    @JsonProperty("familyBurdenKin")
    private Boolean cargaFamiliarPrentesco;

}
