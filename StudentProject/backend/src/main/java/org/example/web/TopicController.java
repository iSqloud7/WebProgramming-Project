package org.example.web;

import org.example.model.DTOs.subjectDTO.CreateSubjectDTO;
import org.example.model.DTOs.subjectDTO.DisplaySubjectDTO;
import org.example.model.DTOs.topicDTO.CreateTopicDTO;
import org.example.model.DTOs.topicDTO.DisplayTopicDTO;
import org.example.service.application.TopicApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicApplicationService topicApplicationService;

    public TopicController(TopicApplicationService topicApplicationService) {
        this.topicApplicationService = topicApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<DisplayTopicDTO>> findAll() {
        return ResponseEntity.ok(this.topicApplicationService.findAll());
    }

    @PostMapping("/add-topic")
    public ResponseEntity<Optional<DisplayTopicDTO>> addTopic(@RequestBody CreateTopicDTO createTopicDTO) {
        return ResponseEntity.ok(this.topicApplicationService.create(createTopicDTO));
    }

    @PutMapping("/edit-topic/{id}")
    public ResponseEntity<Optional<DisplayTopicDTO>> editTopic(@PathVariable Long id,
                                                               @RequestBody CreateTopicDTO createTopicDTO) {
        return ResponseEntity.ok(this.topicApplicationService.update(id, createTopicDTO));
    }

    @DeleteMapping("delete-topic/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        this.topicApplicationService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
