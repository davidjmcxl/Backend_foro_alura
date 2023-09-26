package com.foro.alura.Controller;

import com.foro.alura.Model.DTO.DatesRegisterAndUpdateTopic;
import com.foro.alura.Model.DTO.ListResponseDTO;
import com.foro.alura.Model.DTO.ListTopicsDTO;
import com.foro.alura.Model.DTO.RegisterAndUpdateResponse;
import com.foro.alura.Model.Entities.ResponseTopic;
import com.foro.alura.Model.Entities.Topic;
import com.foro.alura.Service.ResponseTopicService;
import com.foro.alura.Service.TopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/responseTopics")
@SecurityRequirement(name = "bearer-key")
public class ResponseTopicController {
    @Autowired
    ResponseTopicService responseTopicService;

    @GetMapping
    public ResponseEntity<Page<ListResponseDTO> >getResponses(@PageableDefault(size = 10) Pageable paginacion) {
        var respTopics=responseTopicService.getResponsTopics(paginacion);
        return ResponseEntity.ok(respTopics);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ListResponseDTO> getResponseTopicById(@PathVariable Long id) {
        var respTopicByid = responseTopicService.getResponseTopicById(id);

        return   ResponseEntity.ok(respTopicByid);
    }

    @PostMapping
    public ResponseEntity<ListResponseDTO> registerResponseTopic(@RequestBody @Valid RegisterAndUpdateResponse datesRegister) {

        ListResponseDTO respTopicSave = responseTopicService.registerResponseTopic(datesRegister);
        return ResponseEntity.ok().body(respTopicSave);

    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseTopic> updateResponseTopic(@RequestBody @Valid ResponseTopic datesUpdateRespTopic, @PathVariable Long id) {

        ResponseTopic topicRespSave = responseTopicService.UdpdateTopic(datesUpdateRespTopic,id);
        return ResponseEntity.ok().build();

    }
    @DeleteMapping("/{id}")

    public ResponseEntity<Topic> deleteResponseTopic(@PathVariable Long id) {

        responseTopicService.deleteTopic(id);
        return ResponseEntity.noContent().build();

    }
}
