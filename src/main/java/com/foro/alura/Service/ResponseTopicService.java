package com.foro.alura.Service;

import com.foro.alura.Model.DTO.DatesRegisterAndUpdateTopic;
import com.foro.alura.Model.DTO.ListResponseDTO;
import com.foro.alura.Model.DTO.ListTopicsDTO;
import com.foro.alura.Model.DTO.RegisterAndUpdateResponse;
import com.foro.alura.Model.Entities.ResponseTopic;
import com.foro.alura.Model.Entities.Topic;
import com.foro.alura.Repository.ResponseTopicRepository;
import com.foro.alura.Repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ResponseTopicService {
    @Autowired
    private ResponseTopicRepository responseTopicRepository;

    public Page<ListResponseDTO> getResponsTopics(Pageable paginacion) {
        var resptopics = responseTopicRepository.findAll(paginacion).map(ListResponseDTO::new);
        return resptopics;
    }

    public ListResponseDTO registerResponseTopic(RegisterAndUpdateResponse responseTopic) {

        ResponseTopic respTopic = responseTopicRepository.save(new ResponseTopic(responseTopic));
        var respTopicDTO= new ListResponseDTO(respTopic);
        return respTopicDTO;
    }

    public ListResponseDTO getResponseTopicById(Long id) {
        if (!responseTopicRepository.existsById(id)) {
            throw new ValidationException("no existe una respuesta con ese id");
        }
        var responseTopic= responseTopicRepository.findById(id).map(ListResponseDTO::new).get();

        return responseTopic;
    }

    public ResponseTopic UdpdateTopic(ResponseTopic responseTopic, Long id) {

        Optional<ResponseTopic> existingTopicOptional = responseTopicRepository.findById(id);
        if (existingTopicOptional.isPresent()) {
            ResponseTopic existingTopic = existingTopicOptional.get();
            existingTopic.setResponse(responseTopic.getResponse());
            existingTopic.setUser(responseTopic.getUser());
            existingTopic.setFecha_creacion(responseTopic.getFecha_creacion());
            existingTopic.setTopic(responseTopic.getTopic());


            ResponseTopic updatedTopic = responseTopicRepository.save(existingTopic);

            return updatedTopic;
        } else {
            throw new EntityNotFoundException("No se encontró una respuesta con el ID proporcionado");
        }
    }

    public void deleteTopic(Long id) {

        if (!responseTopicRepository.existsById(id)) {
            throw new ValidationException("No se encontró una respuesta con el ID proporcionado");
        }
        responseTopicRepository.deleteById(id);
    }

}
