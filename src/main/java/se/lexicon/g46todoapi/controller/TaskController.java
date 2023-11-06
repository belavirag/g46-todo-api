package se.lexicon.g46todoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g46todoapi.domain.dto.TaskDTOForm;
import se.lexicon.g46todoapi.domain.dto.TaskDTOView;
import se.lexicon.g46todoapi.service.TaskService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTOView>> getAll() {
        return ResponseEntity.ok().body(taskService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TaskDTOView>> findById(@PathVariable long id) {
        return ResponseEntity.ok().body(taskService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Optional<TaskDTOView>> createOrUpdate(@RequestBody TaskDTOForm form) {
        return ResponseEntity.ok().body(taskService.createOrUpdate(form));
    }
}
