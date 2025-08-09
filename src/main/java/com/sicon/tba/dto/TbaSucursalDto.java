package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TbaSucursalDto {

    @NotNull(message = "El id de la ciudad no puede ser nulo")
    @JsonProperty("cityId")
    private Integer idCiudad;

    @NotBlank(message = "El id de la categoria de sucursal no puede ser nulo")
    @JsonProperty("branchCategoryId")
    private String idCategoriaSucursal;

    @NotNull(message = "El id de la region no puede ser nulo")
    @JsonProperty("regionId")
    private Integer idRegion;

    @NotBlank(message = "El nombre de la sucursal no puede estar vacío")
    @JsonProperty("name")
    private String nombreSucursal;

    @NotBlank(message = "El nemonico de la sucursal no puede estar vacío")
    @JsonProperty("mnemonic")
    private String nemonicoSucursal;

    @NotBlank(message = "La dirección de la sucursal no puede estar vacía")
    @JsonProperty("address")
    private String direccionSucursal;

    @JsonProperty("phone")
    private String telefonoSucursal;

    @JsonProperty("phone2")
    private String telefono2Sucursal;

    @JsonProperty("cellphone")
    private String celularSucursal;

    @JsonProperty("email")
    private String correoSucursal;

    @JsonProperty("email2")
    private String correo2Sucursal;

    @JsonProperty("responsible")
    private String responsableSucursal;

    @JsonProperty("isOutlet")
    private Boolean esOutletSucursal;

    @JsonProperty("state")
    private Boolean estadoSucursal;

    @JsonProperty("thermalPrinting")
    private Boolean impresionTermicaSucursal;

    @JsonProperty("branchCode")
    private String codigoSucursal;

    @JsonProperty("wareHouseDetail")
    private List<BodegaDto> bodegaList;
}
