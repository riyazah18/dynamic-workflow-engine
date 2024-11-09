package com.workflow.engine.service;

import com.workflow.engine.entity.Task;
import com.workflow.engine.entity.User;
import com.workflow.engine.entity.WorkflowState;
import com.workflow.engine.entity.WorkflowTransition;
import com.workflow.engine.model.TaskRequestModel;
import com.workflow.engine.repository.TaskRepository;
import com.workflow.engine.repository.UserRepository;
import com.workflow.engine.repository.WorkflowStateRepository;
import com.workflow.engine.repository.WorkflowTransitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final WorkflowTransitionRepository transitionRepository;
    private final WorkflowStateRepository stateRepository;
    private final UserRepository userRepository;

    public Task createTask(TaskRequestModel taskRequestModel) {
        Task task = new Task();
        task.setTitle(taskRequestModel.getTitle());
        task.setDescription(taskRequestModel.getDescription());
        task.setCreatedDate(taskRequestModel.getCreatedDate() != null
                ? taskRequestModel.getCreatedDate()
                : LocalDateTime.now());
        task.setLastUpdatedDate(LocalDateTime.now());

        // Fetch and set current state
        WorkflowState currentState = stateRepository.findById(taskRequestModel.getCurrentStateId())
                .orElseThrow(() -> new IllegalStateException("Invalid state ID"));
        task.setCurrentState(currentState);

        // Fetch and set assignee
        User assignee = userRepository.findById(taskRequestModel.getAssigneeId())
                .orElseThrow(() -> new IllegalStateException("Invalid user ID"));
        task.setAssignee(assignee);

        return taskRepository.save(task);
    }

    public Task updateTaskState(Long taskId, Long toStateId) throws IllegalStateException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalStateException("Task not found"));

        WorkflowState currentState = task.getCurrentState();

        // Check if transition is allowed
        WorkflowTransition transition = (WorkflowTransition) transitionRepository
                .findByFromState_IdAndToState_Id(currentState.getId(), toStateId)
                .orElseThrow(() -> new IllegalStateException("Invalid transition"));

        task.setCurrentState(transition.getToState());
        task.setLastUpdatedDate(LocalDateTime.now());

        return taskRepository.save(task);
    }
}