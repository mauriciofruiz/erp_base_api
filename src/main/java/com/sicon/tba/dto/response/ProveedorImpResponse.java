package com.sicon.tba.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public class ProveedorImpResponse {

    @JsonProperty("providerId")
    private Integer proveedorId;

    @JsonProperty("providerName")
    private String nombreProveedor;

    @JsonProperty("providerAddress")
    private String direccionProveedor;

    @JsonProperty("contact")
    private String contacto;

    @JsonProperty("identification")
    private String identificacion;

    @JsonProperty("emailList")
    private List<String> correoList;

    @JsonProperty("phoneList")
    private List<String> telefonoList;
}
