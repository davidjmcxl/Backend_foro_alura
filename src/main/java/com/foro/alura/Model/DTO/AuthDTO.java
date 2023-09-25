package com.foro.alura.Model.DTO;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO (
        @NotBlank
        String email,
        @NotBlank
        String password
){
}
