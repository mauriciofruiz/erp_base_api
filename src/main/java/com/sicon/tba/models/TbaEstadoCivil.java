package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_ESTADOCIVIL")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaEstadoCivil {
    @Id
    @Column("ID_ESTADOCIVIL")
    @JsonProperty("maritalStatusId")
    private Integer idEstadoCivil;

    @Column("NOMBRE_ESTADOCIVIL")
    @JsonProperty("maritalStatusName")
    private String nombreEstadoCivil;

    @Column("COD_ESTADOCIVIL")
    @JsonProperty("maritalStatusCode")
    private String codigoEstadoCivil;
}
