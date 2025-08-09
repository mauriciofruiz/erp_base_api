package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TbaCategoriaSucursalDto {

    @NotBlank(message = "El Id de la categoría de la sucursal no puede estar vacío")
    @JsonProperty("branchCategoryId")
    private String idCategoriaSucursal;

    @NotBlank(message = "El nombre de la categoría de la sucursal no puede estar vacío")
    @JsonProperty("branchCategoryName")
    private String nombreCategoriaSucursal;

    @JsonProperty("branchCategoryDescription")
    private String descripcionCategoriaSucursal;
}
