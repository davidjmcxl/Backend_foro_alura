package com.foro.alura.Controller;

import com.foro.alura.Model.DTO.DatesRegisterAndUpdateTopic;
import com.foro.alura.Model.DTO.ListUserDTO;
import com.foro.alura.Model.DTO.RegisterUserDTO;
import com.foro.alura.Model.DTO.UpdateUserDTO;
import com.foro.alura.Model.Entities.Topic;
import com.foro.alura.Model.Entities.User;
import com.foro.alura.Service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
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
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ListUserDTO> updateUser(@RequestBody @Valid UpdateUserDTO datesUpdate, @PathVariable Long id) {

        userService.updateUser(datesUpdate,id);
        return ResponseEntity.ok().build();

    }

}
