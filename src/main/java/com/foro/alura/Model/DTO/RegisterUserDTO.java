package com.foro.alura.Model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserDTO(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank

        String password,
        @NotBlank
        String telefono,
        @NotBlank
        String documento

) {


}
