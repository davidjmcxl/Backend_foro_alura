package com.foro.alura.Controller;

import com.foro.alura.Model.DTO.DatesRegisterAndUpdateTopic;
import com.foro.alura.Model.DTO.ListTopicsDTO;
import com.foro.alura.Model.Entities.ResponseTopic;
import com.foro.alura.Model.Entities.Topic;
import com.foro.alura.Service.ResponseTopicService;
import com.foro.alura.Service.TopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/responseTopics")
public class ResponseTopicController {
    @Autowired
    ResponseTopicService responseTopicService;

    @GetMapping
    public ResponseEntity<Page<ResponseTopic>> getResponses(@PageableDefault(size = 10) Pageable paginacion) {
        var respTopics=responseTopicService.getResponsTopics(paginacion);
        return ResponseEntity.ok(respTopics);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTopic> getResponseTopicById(@PathVariable Long id) {
        var respTopicByid = responseTopicService.getResponseTopicById(id);

        return   ResponseEntity.ok( respTopicByid);
    }

    @PostMapping
    public ResponseEntity<ResponseTopic> registerResponseTopic(@RequestBody @Valid ResponseTopic datesRegister) {

        ResponseTopic respTopicSave = responseTopicService.registerResponseTopic(datesRegister);
        return ResponseEntity.ok().body(respTopicSave);

    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseTopic> updateResponseTopic(@RequestBody @Valid ResponseTopic datesUpdateRespTopic, @PathVariable Long id) {

        ResponseTopic topicRespSave = responseTopicService.UdpdateTopic(datesUpdateRespTopic,id);
        return ResponseEntity.ok().body(topicRespSave);

    }
    @DeleteMapping("/{id}")

    public ResponseEntity<Topic> deleteResponseTopic(@PathVariable Long id) {

        responseTopicService.deleteTopic(id);
        return ResponseEntity.noContent().build();

    }
}
