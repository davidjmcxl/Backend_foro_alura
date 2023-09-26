package com.foro.alura.Model.DTO;

import com.foro.alura.Model.Entities.Topic;
import com.foro.alura.Model.Entities.User;
import jakarta.validation.constraints.NotBlank;

public record RegisterAndUpdateResponse(@NotBlank String response , @NotBlank String fecha_creacion , Topic topic, User user) {

}
