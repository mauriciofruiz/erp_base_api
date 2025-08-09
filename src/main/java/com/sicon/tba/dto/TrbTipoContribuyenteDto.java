package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public record TrbTipoContribuyenteDto(
        @JsonProperty("contributorTypeId") @NotNull(message = "El id del tipo de contribuyente no puede estar vacío") String idTipoContribuyente,
        @JsonProperty("contributorTypeName") @NotNull(message = "El nombre del tipo de contribuyente no puede estar vacío") String nombreTipoContribuyente,
        @JsonProperty("contributorTypeCode") @NotNull(message = "El código del tipo de contribuyente no puede estar vacío") String codigoTipoContribuyente) {

}
