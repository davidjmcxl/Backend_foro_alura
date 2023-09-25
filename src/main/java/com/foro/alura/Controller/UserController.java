package com.foro.alura.Controller;

import com.foro.alura.Model.DTO.DatesRegisterAndUpdateTopic;
import com.foro.alura.Model.DTO.ListUserDTO;
import com.foro.alura.Model.DTO.RegisterUserDTO;
import com.foro.alura.Model.Entities.User;
import com.foro.alura.Service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<Page<ListUserDTO>> getUsers(@PageableDefault(size = 10) Pageable paginacion){
        if(userService.getUsers(paginacion).isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userService.getUsers(paginacion));

    }
    @PostMapping
    public ResponseEntity<ListUserDTO> User (@RequestBody  @Valid RegisterUserDTO registerUserDTO){

        var newUser=userService.registerUser(registerUserDTO);
        return ResponseEntity.ok(new ListUserDTO(newUser));
    }

}
