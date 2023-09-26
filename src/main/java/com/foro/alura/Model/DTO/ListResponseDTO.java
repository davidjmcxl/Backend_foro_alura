package com.foro.alura.Model.DTO;

import com.foro.alura.Model.Entities.ResponseTopic;
import com.foro.alura.Model.Entities.Topic;

public record ListResponseDTO(Long id,
                              String response,

                              String fecha  ,


                              Long id_topic,

                              String tituloTopic,
                              String mensajeTopic,
                              Long user_id,
                              String nombreUser
) {
    public ListResponseDTO(ResponseTopic responseTopic) {
        this(responseTopic.getId(), responseTopic.getResponse(), responseTopic.getFecha_creacion(), responseTopic.getTopic().getId(), responseTopic.getTopic().getTitulo(), responseTopic.getTopic().getMensaje(), responseTopic.getUser().getId(), responseTopic.getUser().getNombre());
    }
}