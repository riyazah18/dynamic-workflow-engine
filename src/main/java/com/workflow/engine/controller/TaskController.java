package com.workflow.engine.controller;

import com.workflow.engine.entity.Task;
import com.workflow.engine.model.TaskRequestModel;
import com.workflow.engine.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    
    private final TaskService taskService;
    
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequestModel task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }
    
    @PutMapping("/{id}/state")
    public ResponseEntity<Task> updateTaskState(
            @PathVariable Long id, 
            @RequestParam Long toStateId) {
        Task updatedTask = taskService.updateTaskState(id, toStateId);
        return ResponseEntity.ok(updatedTask);
    }
}