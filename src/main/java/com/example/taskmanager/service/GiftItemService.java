package com.example.taskmanager.service;

import com.example.taskmanager.dto.GiftItemRequest;
import com.example.taskmanager.entity.GiftItem;
import com.example.taskmanager.repository.GıftItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class TaskService {

    private final GıftItemRepository taskRepository;

    public TaskService(GıftItemRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<GiftItem> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<GiftItem> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Transactional
    public GiftItem createTask(GiftItemRequest taskRequest) {
        GiftItem task = new GiftItem();

        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());

        if (taskRequest.getCompleted() != null) {
            task.setCompleted(taskRequest.getCompleted());
        } else {
            task.setCompleted(false);
        }

        return taskRepository.save(task);
    }

    @Transactional
    public GiftItem updateTask(Long id, GiftItemRequest taskRequest) {
        Optional<GiftItem> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            GiftItem existingTask = optionalTask.get();

            existingTask.setTitle(taskRequest.getTitle());
            existingTask.setDescription(taskRequest.getDescription());

            if (taskRequest.getCompleted() != null) {
                existingTask.setCompleted(taskRequest.getCompleted());
            }

            return taskRepository.save(existingTask);
        }

        return null;
    }

    @Transactional
    public boolean deleteTask(Long id) {
        Optional<GiftItem> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            taskRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public List<GiftItem> getTasksByCompletedStatus(boolean completed) {
        return taskRepository.findByCompleted(completed);
    }

    public List<GiftItem> searchTasksByTitle(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<GiftItem> filterTasksByTitleAndCompleted(String title, boolean completed) {
        return taskRepository.findByTitleContainingIgnoreCaseAndCompleted(title, completed);
    }

    public long countTasksByCompletedStatus(boolean completed) {
        return taskRepository.countByCompleted(completed);
    }

    public boolean existsTaskByTitle(String title) {
        return taskRepository.existsByTitleIgnoreCase(title);
    }

    public List<GiftItem> getLatestFiveTasks() {
        return taskRepository.findTop5ByOrderByIdDesc();
    }

    @Transactional
    public GiftItem createTaskWithRollbackTest(GiftItemRequest taskRequest) {
        GiftItem task = new GiftItem();

        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());

        if (taskRequest.getCompleted() != null) {
            task.setCompleted(taskRequest.getCompleted());
        } else {
            task.setCompleted(false);
        }

        GiftItem savedTask = taskRepository.save(task);

        if (true) {
            throw new RuntimeException("Rollback testi için bilinçli hata oluşturuldu.");
        }

        return savedTask;
    }
}