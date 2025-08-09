package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_PROVINCIA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaProvincia {
    @Id
    @Column("ID_PROVINCIA")
    @JsonProperty("provinceId")
    private String idProvincia;

    @Column("ID_PAIS")
    @JsonProperty("countryId")
    private Integer idPais;

    @Column("NOMBRE_PROVINCIA")
    @JsonProperty("provinceName")
    private String nombreProvincia;

    @Column("COD_REGION")
    @JsonProperty("regionCode")
    private Integer codRegion;
}
