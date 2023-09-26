package com.foro.alura.Repository;

import com.foro.alura.Model.DTO.ListUserDTO;
import com.foro.alura.Model.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long > {
        UserDetails findByEmail(String emailUsuario);


        boolean existsByEmail(String email);

        boolean existsByDocumento(String documento);

        boolean existsByIdNotAndEmail(Long id, String email);

        boolean existsByIdNotAndDocumento(Long id, String documento);
}
