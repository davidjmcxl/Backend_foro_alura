package com.foro.alura.Controller;

import com.foro.alura.Model.DTO.AuthDTO;
import com.foro.alura.Model.DTO.AuthenticationResponse;
import com.foro.alura.Model.DTO.ListUserDTO;
import com.foro.alura.Model.DTO.RenewTokenDto;
import com.foro.alura.Model.Entities.User;
import com.foro.alura.Repository.UserRepository;
import com.foro.alura.infra.security.DatosJWTToken;
import com.foro.alura.infra.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@SecurityRequirement(name = "bearer-key")
public class Auth {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<DatosJWTToken> autenticarUsuario(@RequestBody @Valid AuthDTO authDTO) {
        try{
            Authentication authToken = new UsernamePasswordAuthenticationToken(authDTO.email(),
                    authDTO.password());

            var usuarioAutenticado = authenticationManager.authenticate(authToken);
            var JWTtoken = tokenService.generarToken((User) usuarioAutenticado.getPrincipal());
            var detailUser= userRepository.findByEmail(authDTO.email());

            return ResponseEntity.ok(new DatosJWTToken(JWTtoken,new ListUserDTO((User) detailUser)));
        }catch (BadCredentialsException e) {
            throw new ValidationException("Credenciales de inicio incorrectas");
        }

    }
    @GetMapping("/renew")
    public ResponseEntity<RenewTokenDto> renovarToken(@RequestHeader("Authorization") String token) {
        var renewTokenDto= tokenService.renovarToken(token);
      return  ResponseEntity.ok(new RenewTokenDto(renewTokenDto));

    }
}
