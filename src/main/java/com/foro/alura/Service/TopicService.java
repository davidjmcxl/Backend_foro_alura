package com.foro.alura.Service;


import com.foro.alura.Model.DTO.DatesRegisterAndUpdateTopic;
import com.foro.alura.Model.DTO.ListTopicsDTO;
import com.foro.alura.Model.DTO.ListUserDTO;
import com.foro.alura.Model.Entities.Topic;
import com.foro.alura.Repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public Page<ListTopicsDTO> getTopics(Pageable paginacion) {
        var topics=topicRepository.findAll(paginacion).map(ListTopicsDTO::new);
        return topics;
    }

    public Topic registerTopic(DatesRegisterAndUpdateTopic datesRegisterTopic) {

        if (topicRepository.existsByTitulo(datesRegisterTopic.titulo())) {

            throw new ValidationException("El titulo ya existe en otro tema ");
        }
        if (topicRepository.existsByMensaje(datesRegisterTopic.mensaje())) {
            throw new ValidationException("El mensaje  ya existe en otro tema ");
        }

        Topic topic = topicRepository.save(new Topic(datesRegisterTopic));

        return topic;
    }

    public Topic getTopicById(Long id) {
        if (!topicRepository.existsById(id)) {
            throw new ValidationException("no existe tema con ese id");
        }
        Topic topic = topicRepository.findById(id).get();

        return topic;
    }
    public Topic UdpdateTopic(DatesRegisterAndUpdateTopic datesUdateTopic, Long id) {
        if (topicRepository.existsByIdNotAndTitulo(id,datesUdateTopic.titulo())) {

            throw new ValidationException("El titulo ya existe en otro tema ");
        } if (topicRepository.existsByIdNotAndMensaje(id,datesUdateTopic.mensaje())) {

            throw new ValidationException("El mensaje ya existe en otro tema ");
        }
        Optional<Topic> existingTopicOptional = topicRepository.findById(id);
        if (existingTopicOptional.isPresent()) {
            Topic existingTopic = existingTopicOptional.get();
            existingTopic.setTitulo(datesUdateTopic.titulo());
            existingTopic.setUser(datesUdateTopic.user());
            existingTopic.setCurso(datesUdateTopic.curso());
            existingTopic.setMensaje(datesUdateTopic.mensaje());
            existingTopic.setFecha_creacion(datesUdateTopic.fecha_creacion());

            Topic updatedTopic = topicRepository.save(existingTopic);

            return updatedTopic;
        } else {
            throw new EntityNotFoundException("No se encontró un tema con el ID proporcionado");
        }
    }

    public void deleteTopic( Long id) {

       if(!topicRepository.existsById(id)){
           throw new ValidationException("No se encontró un tema con el ID proporcionado");
       }
        topicRepository.deleteById(id);
    }




    }


