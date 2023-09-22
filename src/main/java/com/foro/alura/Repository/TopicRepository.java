package com.foro.alura.Repository;

import com.foro.alura.Model.Entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,Long> {
    boolean existsByTitulo(String titulo);

    boolean existsByMensaje(String mensaje);



    boolean existsByIdNotAndMensaje(Long id, String mensaje);

    boolean existsByIdNotAndTitulo(Long id, String titulo);
}
