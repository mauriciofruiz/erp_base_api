package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_TIPOCUENTABANCARIA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaTipoCuentaBancaria {
    @Id
    @Column("ID_TIPOCUENTABANCARIA")
    @JsonProperty("accountTypeId")
    private Integer idTipoCuentaBancaria;

    @Column("NOMBRE_TIPOCUENTABANCARIA")
    @JsonProperty("accountTypeName")
    private String nombreTipoCuentaBancaria;

    @Column("NEMONICO_TIPOCUENTABANCARIA")
    @JsonProperty("accountTypeMnemonic")
    private String nemonicoTipoCuentaBancaria;
}
