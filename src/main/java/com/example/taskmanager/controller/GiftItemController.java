package com.example.taskmanager.controller;

import jakarta.validation.Valid;
import com.example.taskmanager.dto.GiftItemRequest;
import com.example.taskmanager.entity.GiftItem;
import com.example.taskmanager.service.GiftItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final GiftItemService taskService;

    public TaskController(GiftItemService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<GiftItem> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiftItem> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(task -> ResponseEntity.ok(task))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GiftItem> createTask(@Valid @RequestBody GiftItemRequest taskRequest) {
        GiftItem createdTask = taskService.createTask(taskRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GiftItem> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody GiftItemRequest taskRequest
    ) {
        GiftItem updatedTask = taskService.updateTask(id, taskRequest);

        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        boolean deleted = taskService.deleteTask(id);

        if (deleted) {
            return ResponseEntity.ok("Görev silindi.");
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/completed/{completed}")
    public List<GiftItem> getTasksByCompletedStatus(@PathVariable boolean completed) {
        return taskService.getTasksByCompletedStatus(completed);
    }

    @GetMapping("/search")
    public List<GiftItem> searchTasksByTitle(@RequestParam String title) {
        return taskService.searchTasksByTitle(title);
    }

    @GetMapping("/filter")
    public List<GiftItem> filterTasks(
            @RequestParam String title,
            @RequestParam boolean completed
    ) {
        return taskService.filterTasksByTitleAndCompleted(title, completed);
    }

    @GetMapping("/count")
    public long countTasksByCompletedStatus(@RequestParam boolean completed) {
        return taskService.countTasksByCompletedStatus(completed);
    }

    @GetMapping("/exists")
    public boolean existsTaskByTitle(@RequestParam String title) {
        return taskService.existsTaskByTitle(title);
    }

    @GetMapping("/latest")
    public List<GiftItem> getLatestFiveTasks() {
        return taskService.getLatestFiveTasks();
    }

    @PostMapping("/rollback-test")
    public ResponseEntity<GiftItem> createTaskWithRollbackTest(@Valid @RequestBody GiftItemRequest taskRequest) {
        GiftItem createdTask = taskService.createTaskWithRollbackTest(taskRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);}
}