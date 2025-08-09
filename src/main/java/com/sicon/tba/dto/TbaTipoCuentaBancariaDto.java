package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TbaTipoCuentaBancariaDto {

    @NotBlank(message = "El nombre de tipo cuenta no puede estar vacío")
    @JsonProperty("accountTypeName")
    private String nombreTipoCuentaBancaria;

    @NotBlank(message = "La descripción de la cuenta no puede estar vacío")
    @JsonProperty("accountTypeMnemonic")
    private String nemonicoTipoCuentaBancaria;
}
