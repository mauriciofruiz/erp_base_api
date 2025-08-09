package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("CON_INDICEFINANCIERO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ConIndiceFinanciero {
    @Id
    @Column("ID_INDICEFINANCIERO")
    @JsonProperty("financialIndexId")
    private Integer idIndiceFinanciero;

    @Column("NOMBRE_INDICEFINANCIERO")
    @JsonProperty("financialIndexName")
    private String nombreIndiceFinanciero;
}