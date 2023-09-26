package com.foro.alura.Service;

import com.foro.alura.Model.DTO.AuthDTO;
import com.foro.alura.Model.DTO.ListUserDTO;
import com.foro.alura.Model.DTO.RegisterUserDTO;
import com.foro.alura.Model.DTO.UpdateUserDTO;
import com.foro.alura.Model.Entities.Topic;
import com.foro.alura.Model.Entities.User;
import com.foro.alura.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;

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


    public User updateUser(UpdateUserDTO datesUpdate, Long id) {
        if (userRepository.existsByIdNotAndEmail(id,datesUpdate.email())) {

            throw new ValidationException("El email ya esta registrado con  otro usuario ");
        } if (userRepository.existsByIdNotAndDocumento(id,datesUpdate.documento())) {

            throw new ValidationException("El Documento ya esta registrado con  otro usuario");
        }

        if(userRepository.existsById(id)){
            var userOld= userRepository.findById(id).get();
            userOld.setDocumento(datesUpdate.documento());
            userOld.setEmail(datesUpdate.email());
            userOld.setNombre(datesUpdate.nombre());
            userOld.setTelefono(datesUpdate.telefono());
            User updatedUser = userRepository.save(userOld);

            return updatedUser;
        } else {
            throw new EntityNotFoundException("No se encontró un tema con el ID proporcionado");
        }
    }
}
