package com.foro.alura.Controller;

import com.foro.alura.Model.DTO.DatesRegisterAndUpdateTopic;
import com.foro.alura.Model.Entities.Topic;
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
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    TopicService topicService;

    @GetMapping
    public ResponseEntity<Page<Topic>> getTopics(@PageableDefault(size = 10) Pageable paginacion) {
        var topics=topicService.getTopics(paginacion);
        return ResponseEntity.ok(topics);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        var topicByid = topicService.getTopicById(id);

        return   ResponseEntity.ok( topicByid);
    }

    @PostMapping
    public ResponseEntity<Topic> registerTopic(@RequestBody @Valid DatesRegisterAndUpdateTopic datesRegisterTopic) {

            Topic topicSave = this.topicService.registerTopic(datesRegisterTopic);
            return ResponseEntity.ok().body(topicSave);

    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Topic> updateTopic(@RequestBody @Valid DatesRegisterAndUpdateTopic datesUpdateTopic, @PathVariable Long id) {

        Topic topicSave = this.topicService.UdpdateTopic(datesUpdateTopic,id);
        return ResponseEntity.ok().body(topicSave);

    }
    @DeleteMapping("/{id}")

    public ResponseEntity<Topic> deleteTopic(@PathVariable Long id) {

        this.topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();

    }
}
