package com.foro.alura.Model.DTO;

import com.foro.alura.Model.Entities.Topic;

public record ListTopicsDTO(Long id,
                            String titulo,

                            String mensaje,


                            String fecha_creacion,

                            String estatus,
                            String curso,
                            UserDTO user_id
) {
    public ListTopicsDTO(Topic topic) {
        this(topic.getId(), topic.getTitulo(), topic.getMensaje(), topic.getFecha_creacion(), topic.getEstatus(), topic.getCurso(), new UserDTO(topic.getUser().getId(),topic.getUser().getNombre()));
    }



}