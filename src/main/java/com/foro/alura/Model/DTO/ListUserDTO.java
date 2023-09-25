package com.foro.alura.Model.DTO;

import com.foro.alura.Model.Entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ListUserDTO (

        Long id,
        String nombre,

        String email,


        String telefono,

        String documento
){
    public ListUserDTO(User user) {
        this(user.getId(), user.getNombre(), user.getEmail(),  user.getTelefono(),user.getDocumento());
    }
}
