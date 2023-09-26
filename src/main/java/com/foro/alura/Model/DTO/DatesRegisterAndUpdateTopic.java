package com.foro.alura.Model.DTO;

import com.foro.alura.Model.Entities.User;
import jakarta.validation.constraints.NotBlank;

public record DatesRegisterAndUpdateTopic(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank String fecha_creacion,
        @NotBlank
        String estatus,
        @NotBlank
        String curso,

        User user
) {

}
