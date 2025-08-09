package com.sicon.tba.models.rrh;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("RRH_DISCAPACIDAD")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RrhDiscapacidad {
    @Id
    @Column("ID_DISCAPACIDAD")
    @JsonProperty("disabilityId")
    private Integer idDiscapacidad;

    @Column("NOMBRE_DISCAPACIDAD")
    @JsonProperty("disabilityName")
    private String nombreDiscapacidad;
}
