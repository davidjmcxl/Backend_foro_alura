package com.foro.alura.infra.security;

import com.foro.alura.Model.DTO.ListUserDTO;
import org.springframework.security.core.userdetails.UserDetails;

public record   DatosJWTToken(String jwTtoken , ListUserDTO userDetails) {
}
