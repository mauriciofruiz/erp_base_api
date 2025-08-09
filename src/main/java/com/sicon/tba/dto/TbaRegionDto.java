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
@Builder
public class TbaRegionDto {

    @NotBlank(message = "El nombre de la region no puede estar vacio")
    @JsonProperty("regionName")
    private String nombreRegion;
}
