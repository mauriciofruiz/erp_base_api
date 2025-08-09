package com.sicon.tba.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDto {

    @NotBlank(message = "El nick es requerido")
    private String nick;

    @NotBlank(message = "La contraseña es requerida")
    private String password;
}
