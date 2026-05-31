package com.example.taskmanager.repository;

import com.example.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByCompleted(boolean completed);

    List<Task> findByTitleContainingIgnoreCase(String title);

    List<Task> findByTitleContainingIgnoreCaseAndCompleted(String title, boolean completed);

    long countByCompleted(boolean completed);

    boolean existsByTitleIgnoreCase(String title);

    List<Task> findTop5ByOrderByIdDesc();
}