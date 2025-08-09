package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TbaTipoProveedorDto {

    @NotBlank(message = "El nombre del tipo de proveedor no puede estar vacío")
    @JsonProperty("vendorTypeName")
    private String nombreTipoProv;
}