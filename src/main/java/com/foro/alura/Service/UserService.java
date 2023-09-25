package com.foro.alura.Service;

import com.foro.alura.Model.DTO.AuthDTO;
import com.foro.alura.Model.DTO.ListUserDTO;
import com.foro.alura.Model.DTO.RegisterUserDTO;
import com.foro.alura.Model.Entities.User;
import com.foro.alura.Repository.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<ListUserDTO> getUsers(Pageable paginacion){
        return userRepository.findAll(paginacion).map(ListUserDTO::new);
    }
    public User registerUser(RegisterUserDTO registerUserDTO){
        if (userRepository.existsByEmail (registerUserDTO.email())) {

            throw new ValidationException("El Email ya existe con otro usuario ");
        }
        if (userRepository.existsByDocumento(registerUserDTO.documento())) {
            throw new ValidationException("El documento  ya existe on otro usuario ");
        }
        return userRepository.save(new User(registerUserDTO));
    }




}
