package com.foro.alura.Controller;

import com.foro.alura.Model.DTO.DatesRegisterAndUpdateTopic;
import com.foro.alura.Model.DTO.ListTopicsDTO;
import com.foro.alura.Model.Entities.Topic;
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

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicController {
    @Autowired
    TopicService topicService;

    @GetMapping
    public ResponseEntity<Page<ListTopicsDTO>> getTopics(@PageableDefault(size = 10) Pageable paginacion) {
        var topics=topicService.getTopics(paginacion);
        return ResponseEntity.ok(topics);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ListTopicsDTO> getTopicById(@PathVariable Long id) {
        var topicByid = topicService.getTopicById(id);

        return   ResponseEntity.ok( topicByid);
    }

    @PostMapping
    public ResponseEntity<ListTopicsDTO> registerTopic(@RequestBody @Valid DatesRegisterAndUpdateTopic datesRegisterTopic) {

            ListTopicsDTO topicSave = this.topicService.registerTopic(datesRegisterTopic);
            return ResponseEntity.ok().body(topicSave);

    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Topic> updateTopic(@RequestBody @Valid DatesRegisterAndUpdateTopic datesUpdateTopic, @PathVariable Long id) {

        Topic topicSave = this.topicService.UdpdateTopic(datesUpdateTopic,id);
        return ResponseEntity.ok().build();

    }
    @DeleteMapping("/{id}")

    public ResponseEntity<Topic> deleteTopic(@PathVariable Long id) {

        this.topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();

    }
}
