package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaCuentaBancariaDto {

    @JsonProperty("countBankId")
    private Integer idCuentabancaria;


    @JsonProperty("catalogBank")
    private Integer idBancocatalogo;

    @JsonProperty("bankName")
    private String nombreBancocatalogo;

    @JsonProperty("bankAccountTypeId")
    private Integer idTipocuentabancaria;

    @JsonProperty("accountTypeName")
    private String nombreTipocuentabancaria;

    @JsonProperty("bankAccountNumber")
    private String numeroCuentabancaria;

    @JsonProperty("accountStatusBank")
    private Boolean estadoCuentabancaria;
}
